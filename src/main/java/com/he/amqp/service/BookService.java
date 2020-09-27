package com.he.amqp.service;

import com.he.amqp.bean.Book;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    //监听指定消息队列，可以监听多个消息队列
    @RabbitListener(queues = "atguigu.news")
    public void revice(Book book){

        System.out.println("收到消息："+book.toString());

    }

    @RabbitListener(queues = "atguigu")
    public void revice2(Message message){

        //获取消息的消息体
        System.out.println("收到消息体："+message.getBody());
        //获取消息的消息头
        System.out.println("收到消息头："+message.getMessageProperties());

    }

}
