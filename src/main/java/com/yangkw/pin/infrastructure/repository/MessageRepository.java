package com.yangkw.pin.infrastructure.repository;

import com.yangkw.pin.domain.chat.MessageDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 类MessageRepository.java
 *
 * @author kaiwen.ykw 2018-12-30
 */
@Mapper
public interface MessageRepository {
    Integer inert(@Param("messageDO") MessageDO messageDO);

    Integer findExist(@Param("orderId") Integer orderId);

    List<MessageDO> queryAll(@Param("orderId") Integer orderId);
}
