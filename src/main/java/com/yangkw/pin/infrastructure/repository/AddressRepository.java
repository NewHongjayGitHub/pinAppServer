package com.yangkw.pin.infrastructure.repository;

import com.yangkw.pin.domain.address.AddressDO;
import com.yangkw.pin.domain.address.Dot;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 类AddressRepository.java
 *
 * @author kaiwen.ykw 2018-12-27
 */
@Mapper
public interface AddressRepository {

    int insert(@Param("addressDO") AddressDO addressDO);

    AddressDO find(@Param("dot") Dot dot);

    void addPoint(@Param("id") Integer id);

    AddressDO findById(@Param("id") Integer id);
}
