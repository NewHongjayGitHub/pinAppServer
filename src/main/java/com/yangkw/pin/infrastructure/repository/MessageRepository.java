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
    /**
     * 增加Message
     *
     * @param messageDO
     * @return
     */
    Integer inert(@Param("messageDO") MessageDO messageDO);

    /**
     * 查询是否有message
     *
     * @param orderId
     * @return
     */
    Integer findExist(@Param("orderId") Integer orderId);

    /**
     * 查询所有message
     *
     * @param orderId
     * @return
     */
    List<MessageDO> queryAll(@Param("orderId") Integer orderId);
}
