package com.bcht.axletempmonitor;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan("com.bcht.axletempmonitor.mapper")
@EnableCaching
@EnableTransactionManagement
public class AxletempmonitorApplication {

    public static void main(String[] args) {
        SpringApplication.run(AxletempmonitorApplication.class, args);
    }


}
