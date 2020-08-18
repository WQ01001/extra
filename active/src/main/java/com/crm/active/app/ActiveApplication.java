package com.crm.active.app;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;

/**
 * @program: extra
 * @description:
 * @author: wq
 * @create: 2020-08-13 14:27
 */
@SpringBootApplication
@ComponentScan(
    basePackages = {
      "com.crm.active.config",
      "com.crm.active.controller",
      "com.crm.common",
      "com.crm.active.serviceimpl"
    })
@MapperScan("com.crm.active.mapper")
@ServletComponentScan(basePackages = {"com.crm.active.filter"})
public class ActiveApplication {
  public static void main(String[] args) {
    SpringApplication.run(ActiveApplication.class, args);
  }
}
