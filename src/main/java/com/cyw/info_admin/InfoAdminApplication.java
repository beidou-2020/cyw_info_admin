package com.cyw.info_admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
//扫描mapper接口
@MapperScan(value = "com.cyw.info_admin.dao")
@EnableTransactionManagement
public class InfoAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(InfoAdminApplication.class, args);
    }

}
