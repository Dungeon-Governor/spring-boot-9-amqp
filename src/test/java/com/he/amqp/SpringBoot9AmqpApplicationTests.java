package com.he.amqp;

import com.he.amqp.bean.Book;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class SpringBoot9AmqpApplicationTests {

    @Autowired
    AmqpAdmin amqpAdmin;

    @Autowired
    RabbitTemplate rabbitTemplate;

    //用AmqpAdmin创建一个交换器
    @Test
    void createExchange(){

        //创建一个Direct交换器
        amqpAdmin.declareExchange(new DirectExchange("amqpadmin.exchange"));
        System.out.println("创建完成");

    }

    //用AmqpAdmin创建一个队列
    @Test
    void createQueue(){

        //创建一个队列
        amqpAdmin.declareQueue(new Queue("amqpadmin.queue"));
        System.out.println("创建完成");

    }

    //用AmqpAdmin创建一个绑定规则
    @Test
    void createBinding(){

        //创建一个绑定规则
        //new Binding(目的地,目的地类型,交换器,路由键,参数)
        amqpAdmin.declareBinding(new Binding("amqpadmin.queue", Binding.DestinationType.QUEUE,"amqpadmin.exchange","amqp.haha",null));
        System.out.println("创建完成");

    }

    @Test
    void contextLoads() {
        //Object默认当成消息体，只需要传入要发送的对象，就会自动序列化并发给RabbitMQ
        //rabbitTemplate.convertAndSend(交换器,路由键,Object);
        Map map = new HashMap();
        map.put("msg","这是一个消息");
        map.put("data", Arrays.asList("hello world",123456,true));
        //map会被以java的方式序列化后发送出去
        rabbitTemplate.convertAndSend("exchange.fanout","",new Book("红楼梦","曹雪芹"));
    }

    @Test
    void receive(){
        //接收数据
        //此方法会将接收到的消息转化为序列化之前的对象
        //rabbitTemplate.receiveAndConvert(队列名);
        Object o = rabbitTemplate.receiveAndConvert("atguigu.news");
        System.out.println(o.getClass());
        System.out.println(o);
    }

}
