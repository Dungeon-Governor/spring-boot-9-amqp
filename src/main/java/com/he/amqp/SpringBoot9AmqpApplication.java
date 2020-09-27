package com.he.amqp;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/*
自动配置
1.RabbitAutoConfiguration
2.自动配置了连接工厂ConnectionFactory
3.RabbitProperties封装了RabbitMQ的配置
4.RabbitTemplate用来给Rabbit发送和接收消息
5.Amqp：RabbitMQ系统管理功能组件
 */

//开启基于注解的RabbitMQ
@EnableRabbit
@SpringBootApplication
public class SpringBoot9AmqpApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBoot9AmqpApplication.class, args);

    }

}
