package com.yangkw.pin.infrastructure.repository;

import com.yangkw.pin.domain.order.OrderDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 类OrderRepository.java
 *
 * @author kaiwen.ykw 2018-12-27
 */
@Mapper
public interface OrderRepository {
    Integer insert(@Param("orderDO") OrderDO orderDO);

    List<OrderDO> findAll(@Param("ids") List<Integer> ids);

    OrderDO find(@Param("id") Integer id);

    Integer addCurrentNum(@Param("id") Integer id);

    Integer delCurrentNum(@Param("id") Integer id);

    Integer logicDelete(@Param("id") Integer id);

    Integer updateLeader(@Param("orderId") Integer orderId, @Param("id") Integer id);
}
