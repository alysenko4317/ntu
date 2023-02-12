package com.khpi.demo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main
{
    public static void main(String[] args)
    {
        //ApplicationContext context = new
        //    ClassPathXmlApplicationContext("ru.ivmiit\\context.xml");

        ApplicationContext context =
            new AnnotationConfigApplicationContext(AppConfig.class);

        //System.out.println("Hello");
        IndependentMessageSender renderer = context.getBean(IndependentMessageSender.class);
        renderer.send();
    }
}
