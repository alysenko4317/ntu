package com.khpi.demo1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main
{
    public static void main(String[] args)
    {
        // DI container
        //   - ApplicationContext - интерфйс DI-контейнера
        //   - одна из реализаций ClassPathXmlApplicationContext этого интерфейса

        ApplicationContext ctx =
            new ClassPathXmlApplicationContext("com.khpi.demo1/context.xml");

        System.out.println("*** demo 1 ***");

        MessageSender ms = (MessageSender) ctx.getBean("MessageSender");
        ms.sendMessage("It works!");
    }
}
