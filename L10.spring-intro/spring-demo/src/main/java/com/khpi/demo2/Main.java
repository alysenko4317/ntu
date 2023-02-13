package com.khpi.demo2;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main
{
    public static void main(String[] args)
    {
        ApplicationContext ctx =
            new ClassPathXmlApplicationContext("com.khpi.demo2/context.xml");

        System.out.println("*** demo 2 ***");

        MessageSender ms = (MessageSender) ctx.getBean("MessageSender");

        ms.send();
    }
}
