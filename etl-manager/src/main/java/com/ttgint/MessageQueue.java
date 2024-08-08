package com.ttgint;


import org.springframework.stereotype.Controller;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Controller
public class MessageQueue {

    private static final BlockingQueue<String> queue = new LinkedBlockingQueue<>();

    public boolean produce(String message) throws InterruptedException {
        queue.put(message);
        return true;
    }

    public String consume() throws InterruptedException {
        if (!queue.isEmpty()) return queue.take();
        return "";
    }

    public int getMessageCount() {
        return queue.size();
    }

}
