package com.echoes.system;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

/**
 * @author Lao
 * @version 1.0
 * @description: TODO
 * @date 2022/11/28 6:43 PM
 */
@SpringBootApplication
@MapperScan("com.echoes.system.mapper")
public class SpringBootApplicationAuth {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootApplicationAuth.class, args);
    }
}
