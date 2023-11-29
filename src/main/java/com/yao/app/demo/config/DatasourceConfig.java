package com.yao.app.demo.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.yao.app.demo.dependency.mysql")
public class DatasourceConfig {

}
