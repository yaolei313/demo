package com.yao.app.demo.dependency.mysql.mapper;

import com.yao.app.demo.dependency.mysql.bean.User;

public interface UserMapper {

    /**
     * 描述:
     *
     * @param record
     */
    int insert(User record);

    /**
     * 描述:
     *
     * @param id
     */
    User selectByPrimaryKey(Long id);

    Long selectByEmail(String email);

    /**
     * 描述:
     *
     * @param record
     */
    int updateByPrimaryKeySelective(User record);

    /**
     * 描述:
     *
     * @param record
     */
    int updateByPrimaryKey(User record);
}