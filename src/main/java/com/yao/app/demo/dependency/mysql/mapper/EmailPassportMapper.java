package com.yao.app.demo.dependency.mysql.mapper;

import com.yao.app.demo.dependency.mysql.bean.EmailPassport;

public interface EmailPassportMapper {

    int deleteByEmail(String email);

    int insert(EmailPassport record);

    Long selectUserIdByEmail(String email);

}