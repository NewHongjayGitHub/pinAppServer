package com.yangkw.pin.api;

import com.yangkw.pin.domain.order.Order;
import com.yangkw.pin.domain.request.FuzzyOrderRequest;
import com.yangkw.pin.domain.request.PublishOrderRequest;
import com.yangkw.pin.domain.response.BaseResponse;
import com.yangkw.pin.domain.response.FuzzyOrderResponse;
import com.yangkw.pin.service.OrderService;
import com.yangkw.pin.service.annotation.ParamCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * 类TripController.java的实现描述：TODO
 *
 * @author kaiwen.ykw 2018-12-26
 */
@RestController
@RequestMapping("order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("fuzzy")
    @ParamCheck
    public FuzzyOrderResponse fuzzy(@RequestBody @Validated FuzzyOrderRequest request, BindingResult bindingResult) {
        FuzzyOrderResponse response = new FuzzyOrderResponse();
        List<Order> orderList = orderService.findOrderList(request);
        response.setSuccess(true);
        response.setOrderList(orderList);
        return response;
    }

    @PostMapping("publish")
    @ParamCheck
    public BaseResponse publish(@RequestBody @Validated PublishOrderRequest request, BindingResult bindingResult) {
        BaseResponse response = new BaseResponse();
        orderService.publish(request);
        response.setSuccess(true);
        return response;
    }
}
