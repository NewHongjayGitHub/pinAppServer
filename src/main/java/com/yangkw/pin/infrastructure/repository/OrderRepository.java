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
    /**
     * 新增order
     *
     * @param orderDO
     * @return
     */
    Integer insert(@Param("orderDO") OrderDO orderDO);

    /**
     * 查询所有order
     *
     * @param ids
     * @return
     */
    List<OrderDO> findAll(@Param("ids") List<Integer> ids);

    /**
     * 查询order
     *
     * @param id
     * @return
     */
    OrderDO find(@Param("id") Integer id);

    /**
     * 增加当前人数
     *
     * @param id
     * @return
     */
    Integer addCurrentNum(@Param("id") Integer id);

    /**
     * 减少当前人数
     *
     * @param id
     * @return
     */
    Integer delCurrentNum(@Param("id") Integer id);

    /**
     * 逻辑删除订单
     *
     * @param id
     * @return
     */
    Integer logicDelete(@Param("id") Integer id);

    /**
     * 更新leader
     *
     * @param orderId
     * @param id
     * @return
     */
    Integer updateLeader(@Param("orderId") Integer orderId, @Param("id") Integer id);
}
