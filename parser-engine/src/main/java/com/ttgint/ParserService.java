package com.ttgint;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ttgint.core.EtlCoreService;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class ParserService {

    @Autowired
    private EtlCoreService etlCoreService;

    @Scheduled(fixedDelay = 5000)
    public void getMessageTask() {
        int threadCount = etlCoreService.getRandomInt();
        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
        for (int i = 0; i < threadCount; i++) executorService.execute(this::processParse);
        executorService.shutdown();
    }

    public void processParse() {
        try {
            Response response = etlCoreService.sendRequest(
                    "http://0.0.0.0:8080/api/getMessage",
                    "get",
                    null
            );

            String message = new ObjectMapper().convertValue(response.body().string(), String.class);
            if (!message.isEmpty()) log.info("dosya parse edildi: {} -> {}", Thread.currentThread().getName(), message);
            TimeUnit.MILLISECONDS.sleep(etlCoreService.getRandomInt() * 10);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
