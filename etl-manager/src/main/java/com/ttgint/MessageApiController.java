package com.ttgint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class MessageApiController {

    @Autowired
    private MessageQueue messageQueue;

    @PostMapping("/addMessage")
    public Boolean addMessage(@RequestBody String message) throws InterruptedException {
        return messageQueue.produce(message);
    }

    @GetMapping("/getMessage")
    public String getMessage() throws InterruptedException {
        return messageQueue.consume();
    }

}