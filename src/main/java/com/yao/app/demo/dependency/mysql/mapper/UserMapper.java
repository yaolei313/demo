package com.yao.app.demo.dependency.mysql.mapper;

import com.yao.app.demo.dependency.mysql.bean.User;

public interface UserMapper {

    /**
     * 描述: 不使用useGeneratedKeys="true" keyColumn="id" keyProperty="id"
     */
    int insert(User record);

    /**
     * 描述:
     */
    User selectByUserId(Long userId);

    /**
     * 描述:
     */
    int updateByUserId(User record);

}