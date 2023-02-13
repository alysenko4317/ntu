package com.khpi.demo3;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main
{
    public static void main(String[] args)
    {
        ApplicationContext ctx =
            new ClassPathXmlApplicationContext("com.khpi.demo3/context.xml");

        System.out.println("*** demo 3 ***");

        IndependentMessageSender ms = (IndependentMessageSender) ctx.getBean("IndependentSender");

        ms.send();
    }
}
