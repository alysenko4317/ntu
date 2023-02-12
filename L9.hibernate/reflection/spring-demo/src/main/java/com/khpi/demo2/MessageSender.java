package com.khpi.demo2;

public class MessageSender {

    private IMessage _msg;

    MessageSender(final IMessage msg) {
        _msg = msg;
    }

    public void send() {
        System.out.println(_msg.getText());
    }
}
