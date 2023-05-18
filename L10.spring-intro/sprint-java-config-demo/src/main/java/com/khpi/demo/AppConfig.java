package com.khpi.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

// по сути - это замена конфигурационному context.xml файлу для spring framework

@Configuration
@ComponentScan(basePackages = "com.khpi.demo")
public class AppConfig
{
    @Bean
    public IMessage message() {
        return new HtmlMessage("Hello");
    }

    @Bean
    public IMessageSender messageSender() {
        return new EmailMessageSenderImpl(message());
    }
}
