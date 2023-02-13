package com.khpi.demo1;

public class MessageSender {

    private String _header;

    MessageSender(final String header) {
        _header = header;
    }

    public void sendMessage(final String msg) {
        System.out.println("[" + _header + "] " + msg);
    }
}
