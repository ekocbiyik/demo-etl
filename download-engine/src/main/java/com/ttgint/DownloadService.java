package com.ttgint;

import com.ttgint.core.EtlCoreService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DownloadService {

    @Autowired
    private EtlCoreService etlCoreService;

    private static Long counter = 0L;

    @Scheduled(fixedDelay = 5000)
    public void downloadTask() throws Exception {
        for (int i = 0; i < etlCoreService.getRandomInt(); i++) {

            String message = String.format("message_%s_%s", counter, etlCoreService.getCurrentTime());

            etlCoreService.sendRequest(
                    "http://0.0.0.0:8080/api/addMessage",
                    "post",
                    message
            );
            counter++;

            log.info("dosya download edildi: {} -> {}", Thread.currentThread().getName(), message);
            // NŞA'da bu şekilde olmaması gerekir, down olduğu durumda tüm kayıtlar boşa çıkacaktır.
            // Bu kısımda DB'ye yazması daha uygun..
        }
    }

}
