package com.khpi.demo3;

public class ConsoleMessageSenderImpl implements IMessageSender{

    private IMessage _msg;

    ConsoleMessageSenderImpl(final IMessage msg) {
        _msg = msg;
    }

    public void send() {
        System.out.println("[ConsoleMessageSenderImpl] " + _msg.getText());
    }
}
