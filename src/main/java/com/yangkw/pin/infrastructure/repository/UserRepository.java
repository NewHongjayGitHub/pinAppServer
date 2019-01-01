package com.yangkw.pin.infrastructure.repository;

import com.yangkw.pin.domain.chat.UserInfoForChat;
import com.yangkw.pin.domain.user.UserDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 类UserMapper.java的实现描述：TODO
 *
 * @author kaiwen.ykw 2018-12-21
 */
@Mapper
public interface UserRepository {
    /**
     * 新增用户
     *
     * @param userDO
     * @return
     */
    int insert(@Param("userDO") UserDO userDO);

    /**
     * 查找用户
     *
     * @param openId
     * @return
     */
    UserDO find(@Param("openId") String openId);

    String findOpenId(@Param("userId") Integer userId);

    UserInfoForChat findChatInfo(@Param("userId") Integer userId);

    /**
     * 更新用户
     *
     * @param userDO
     * @return
     */
    int update(@Param("userDO") UserDO userDO);
}
