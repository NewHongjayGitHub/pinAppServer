package com.yangkw.pin.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.google.common.collect.Sets;
import com.yangkw.pin.domain.chat.MessageBody;
import com.yangkw.pin.domain.chat.UserInfoForChat;
import com.yangkw.pin.service.CacheService;
import com.yangkw.pin.service.MessageService;
import com.yangkw.pin.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 类ChatRoomWS.java
 *
 * @author kaiwen.ykw 2018-12-29
 */
@ServerEndpoint(value = "/chatRoom/{orderId}/{token}")
@Component
public class ChatRoomWebSocket {
    private static Logger LOG = LoggerFactory.getLogger(ChatRoomWebSocket.class);
    private static CacheService cacheService;
    private static UserService userService;
    private static MessageService messageService;
    private static Map<Integer, Set<Session>> sessionSet = new ConcurrentHashMap<>();
    private static Map<Session, UserInfoForChat> infoMap = new ConcurrentHashMap<>();
    private Integer orderId;
    private Session session;
    private Integer userId;

    public ChatRoomWebSocket() {
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

    @Autowired
    public void setCacheService(CacheService cacheService) {
        ChatRoomWebSocket.cacheService = cacheService;
    }

    @Autowired
    public void setMessageService(MessageService messageService) {
        ChatRoomWebSocket.messageService = messageService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        ChatRoomWebSocket.userService = userService;
    }

    @OnOpen
    public void onOpen(Session session, @PathParam("orderId") Integer orderId, @PathParam("token") String token) throws IOException {
        Integer userId = cacheService.getUserId(token);
        if (userId == null) {
            session.getBasicRemote().sendText("LOGIN");
        } else {
            this.session = session;
            this.orderId = orderId;
            this.userId = userId;
            UserInfoForChat info = userService.findChatInfo(userId);
            if (sessionSet.containsKey(orderId)) {
                sessionSet.get(orderId).add(session);
            } else {
                Set<Session> sessions = Sets.newConcurrentHashSet();
                sessions.add(session);
                sessionSet.putIfAbsent(orderId, sessions);
            }
            infoMap.put(session, info);
            LOG.info("onOpen info:{}", info);
            if (messageService.isExist(orderId)) {
                List<MessageBody> messageBodyList = messageService.queryAll(orderId, userId);
                String a = JSONArray.toJSONString(messageBodyList, SerializerFeature.DisableCircularReferenceDetect);
                session.getBasicRemote().sendText(a);
            }
        }


    }

    @OnMessage
    public void onMessage(String message, Session session) {
        UserInfoForChat info = infoMap.get(session);
        MessageBody messageBody = new MessageBody(info, message);
        messageBody.setOwnMsg(false);
        sessionSet.get(orderId).parallelStream().filter(s -> s != session)
                .forEach(m -> {
                    try {
                        m.getBasicRemote()
                                .sendText(JSON.toJSONString(messageBody));
                    } catch (IOException e) {
                        LOG.error("IOException info:{} e:{}", info, e.getMessage());
                    }
                });
        messageService.insert(messageBody, userId, orderId);
    }

    @OnClose
    public void close() {
        if (orderId == null) {
            return;
        }
        Set<Session> sessions = sessionSet.get(orderId);
        if (!sessions.isEmpty()) {
            sessions.remove(session);
        } else {
            sessionSet.remove(orderId);
        }
    }

    @OnError
    public void onError(Session session, Throwable error) {
        LOG.error("chatRoom发生错误！info：{} e:{}", infoMap.get(session), error.getMessage());
    }
}