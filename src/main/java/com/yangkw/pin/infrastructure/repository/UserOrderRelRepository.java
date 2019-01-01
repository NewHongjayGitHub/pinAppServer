package com.yangkw.pin.infrastructure.repository;

import com.yangkw.pin.domain.relation.UserOrderRelDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 类UserOrderRelRepository.java
 *
 * @author kaiwen.ykw 2018-12-27
 */
@Mapper
public interface UserOrderRelRepository {

    Integer insert(@Param("userOrderRelDO") UserOrderRelDO userOrderRelDO);

    List<Integer> queryPartner(@Param("orderId") Integer orderId);

    Integer exit(@Param("orderId") Integer orderId, @Param("userId") Integer userId);

    List<Integer> queryOwnOrderList(@Param("userId") Integer userId, @Param("page") Integer page);

    Integer logicDelete(@Param("orderId") Integer orderId, @Param("userId") Integer userId);

    Integer isLeader(@Param("orderId") Integer orderId, @Param("userId") Integer userId);

    Integer updateLeader(@Param("orderId") Integer orderId, @Param("userId") Integer userId);
}
