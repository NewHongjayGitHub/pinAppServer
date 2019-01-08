package com.yangkw.pin.service;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaTemplateData;
import cn.binarywang.wx.miniapp.bean.WxMaTemplateMessage;
import com.google.common.base.Preconditions;
import com.yangkw.pin.domain.address.GeoAddress;
import com.yangkw.pin.domain.address.PublishResult;
import com.yangkw.pin.domain.order.Order;
import com.yangkw.pin.domain.order.OrderDO;
import com.yangkw.pin.domain.order.TimeDTO;
import com.yangkw.pin.domain.relation.UserOrderRelDO;
import com.yangkw.pin.domain.request.FuzzyOrderRequest;
import com.yangkw.pin.domain.request.PartnerOrderRequest;
import com.yangkw.pin.domain.request.PublishOrderRequest;
import com.yangkw.pin.infrastructure.cache.TemplateCache;
import com.yangkw.pin.infrastructure.repository.OrderRepository;
import com.yangkw.pin.infrastructure.repository.UserOrderRelRepository;
import com.yangkw.pin.infrastructure.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 类OrderService.java
 *
 * @author kaiwen.ykw 2018-12-27
 */
@Service
public class OrderService {
    private static Logger LOG = LoggerFactory.getLogger(OrderService.class);

    @Autowired
    private CacheService cacheService;
    @Autowired
    private AddressService addressService;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserOrderRelRepository userOrderRelRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TransactionTemplate transactionTemplate;
    @Autowired
    private WxMaService wxMaService;
    @Value("${templateId}")
    private String templateId;
    @Autowired
    private TemplateCache templateCache;

    public List<Order> findOrderList(FuzzyOrderRequest request) {
        List<Integer> orderids = cacheService.findNearOrderId(request);
        if (orderids.isEmpty()) {
            return Collections.emptyList();
        }
        List<OrderDO> orderDOList = orderRepository.findAll(orderids);
        if (orderids.isEmpty()) {
            LOG.error("orderDOList is null,orderIds :{}", orderids);
            return Collections.emptyList();
        }
        return orderDOList.stream().map(this::assemble).collect(Collectors.toList());
    }

    public List<Order> findOwnOrderList(String token) {
        Integer userId = cacheService.getUserId(token);

        List<Integer> orderIdList = userOrderRelRepository.queryOwnOrderList(userId);
        if (orderIdList.isEmpty()) {
            return Collections.emptyList();
        }
        List<OrderDO> orderDOList = orderRepository.findAll(orderIdList);
        for (OrderDO o : orderDOList) {
            if (o.getTargetTime().isBefore(LocalDateTime.now())) {
                transactionTemplate.execute(status -> {
                            userOrderRelRepository.logicDelete(o.getId(), userId);
                            orderRepository.logicDelete(o.getId());
                            return true;
                        }
                );
            }
        }
        return orderDOList.stream().map(o -> assembleLeader(o, userId)).collect(Collectors.toList());
    }

    private Integer solvePage(Integer p, Integer dynamic) { //0 5 10
        if (p <= 0) {
            return 0;
        } else {
            return dynamic * p;
        }

    }

    public Order findOrder(Integer id, Integer userId) {
        OrderDO orderDO = orderRepository.find(id);
        Preconditions.checkState(orderDO != null, "findOrder error id" + id);
        return assembleLeader(orderDO, userId);
    }


    public Boolean publish(PublishOrderRequest request) {
        Integer userId = cacheService.getUserId(request.getToken());
        PublishResult publishResult = addressService.publish(request.getStartAddress(), request.getEndAddress());
        OrderDO orderDO = assemble(request, publishResult, userId);
        if (orderDO.getTargetTime().isBefore(LocalDateTime.now())) {
            return false;
        }
        transactionTemplate.execute(status -> {
            Integer row = orderRepository.insert(orderDO);
            Preconditions.checkState(row == 1, "order insert fail orderDO:" + orderDO);
            Integer relRow = userOrderRelRepository.insert(construct(orderDO.getId(), userId, true));
            Preconditions.checkState(relRow == 1, "Relation insert fail orderDO" + orderDO);
            return true;
        });
        cacheService.publishCache(request, orderDO.getId());
        return true;
    }

    public Boolean join(PartnerOrderRequest request) {
        Integer orderId = request.getOrderId();
        Integer userId = cacheService.getUserId(request.getToken());
        Integer exitRow = userOrderRelRepository.exit(orderId, userId);
        if (exitRow != null) {
            return false;
        }
        transactionTemplate.execute(status -> {
            Integer row = orderRepository.addCurrentNum(orderId);
            Preconditions.checkState(row == 1, "addCurrentNum fail orderId:" + orderId);
            Integer relRow = userOrderRelRepository.insert(construct(orderId, userId, false));
            Preconditions.checkState(relRow == 1, "relation insert fail orderId:" + orderId);
            return true;
        });
        notifyService(orderId, userId, request.getFormId());
        return true;
    }

    @Async
    public void cancel(PartnerOrderRequest request) {
        Integer orderId = request.getOrderId();
        Integer userId = cacheService.getUserId(request.getToken());
        transactionTemplate.execute(status -> {
            Integer row = orderRepository.delCurrentNum(orderId);
            Preconditions.checkState(row == 1, "delCurrentNum fail orderId:" + orderId);
            OrderDO orderDO = orderRepository.find(orderId);
            if (orderDO.getCurrentNum() < 1) {
                orderRepository.logicDelete(orderId);
            } else {
                Boolean isLeader = userOrderRelRepository.isLeader(orderId, userId) != null;
                if (isLeader) {
                    List<Integer> ids = userOrderRelRepository.queryPartner(orderId);
                    Integer id = ids.stream().filter(x -> !x.equals(userId)).findFirst().get();
                    userOrderRelRepository.updateLeader(orderId, id);
                    orderRepository.updateLeader(orderId, id);
                }
            }
            Integer relRow = userOrderRelRepository.logicDelete(orderId, userId);
            Preconditions.checkState(relRow == 1, "relation delete fail orderId:" + orderId);
            return true;
        });
    }

    private UserOrderRelDO construct(Integer orderId, Integer userId, Boolean leader) {
        UserOrderRelDO userOrderRelDO = new UserOrderRelDO();
        userOrderRelDO.setOrderId(orderId);
        userOrderRelDO.setUserId(userId);
        userOrderRelDO.setLeader(leader);
        userOrderRelDO.setDeleted(false);
        return userOrderRelDO;
    }

    private OrderDO assemble(PublishOrderRequest request, PublishResult result, Integer userId) {
        OrderDO orderDO = new OrderDO();
        TimeDTO time = request.getTimeDTO();
        orderDO.setStartAddressId(result.getStartId());
        orderDO.setEndAddressId(result.getEndId());
        orderDO.setCreator(userId);
        orderDO.setLeader(userId);
        orderDO.setTargetTime(LocalDateTime.of(time.getYear(), time.getMonth(), time.getDay(), time.getHour(), time.getMinute()));
        orderDO.setTargetNum(request.getTargetNum());
        orderDO.setCurrentNum(1);
        orderDO.setDeleted(false);
        return orderDO;
    }

    private Order assembleLeader(OrderDO orderDO, Integer userId) {
        return assemble(orderDO).setLeader(orderDO.getLeader().equals(userId));
    }

    private Order assemble(OrderDO orderDO) {
        Order order = new Order();
        order.setId(orderDO.getId());
        order.setStartAddress(addressService.queryGeoAddress(orderDO.getStartAddressId()));
        order.setEndAddress(addressService.queryGeoAddress(orderDO.getEndAddressId()));
        order.setPublishTime(transfer(orderDO.getGmtCreate()));
        order.setUpdateTime(transfer(orderDO.getGmtModified()));
        order.setTargetTime(transfer(orderDO.getTargetTime()));
        order.setOrderItem(orderDO.getTargetTime());
        order.setTargetNum(orderDO.getTargetNum());
        order.setCurrentNum(orderDO.getCurrentNum());
        return order;
    }

    private String transfer(LocalDateTime time) {
        return time.toString().replace("T", " ");
    }

    @Async
    public void notifyService(Integer orderId, Integer userId, String formId) {
        OrderDO orderDO = orderRepository.find(orderId);
        if (orderDO.getLeader().equals(userId)) {
            return;
        }
        GeoAddress startAddress = addressService.queryGeoAddress(orderDO.getStartAddressId());
        GeoAddress endAddress = addressService.queryGeoAddress(orderDO.getEndAddressId());
        String title = startAddress.getName() + "=>" + endAddress.getName() + "的拼车队伍";
        String name = userRepository.findChatInfo(userId).getNickName();
        String time = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date());
        String warn = "请尽快与拼车友联系哦";
        String[] params = new String[]{title, name, time, warn};
        List<Integer> userIds = userOrderRelRepository.queryPartner(orderId);
        if (userIds.isEmpty()) {
            return;
        }
        userIds.parallelStream().filter(x -> !x.equals(userId)).map(x -> {
                    templateNotify(userRepository.findOpenId(x), templateCache.getTemplateId(x), params);
                    return true;
                }
        );
    }

    private void templateNotify(String receiver, String formId, String[] params) {
        WxMaTemplateMessage msg = new WxMaTemplateMessage();
        msg.setTemplateId(templateId);
        msg.setToUser(receiver);
        msg.setFormId(formId);
        msg.setData(createMsgData(params));
        try {
            wxMaService.getMsgService().sendTemplateMsg(msg);
        } catch (Exception e) {
            LOG.error("send Template Message error,receiver:{}", receiver);
        }
    }

    private List<WxMaTemplateData> createMsgData(String[] parms) {
        List<WxMaTemplateData> dataList = new ArrayList<>();
        for (int i = 1; i <= parms.length; i++) {
            dataList.add(new WxMaTemplateData("keyword" + i, parms[i - 1]));
        }
        return dataList;
    }
}
