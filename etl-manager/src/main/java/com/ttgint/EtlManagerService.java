package com.ttgint;

import com.ttgint.core.EtlCoreService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EtlManagerService {

    @Autowired
    private EtlCoreService etlCoreService;

    @Autowired
    private MessageQueue messageQueue;

    @Scheduled(fixedDelay = 5000)
    public void generateMessageTask() throws InterruptedException {
        log.info("Kuyrukta bekleyen mesaj sayısı: {}", messageQueue.getMessageCount());
    }

//    @PostConstruct
//    public void test() {
//        ExecutorService executorService = Executors.newFixedThreadPool(5);
//        for (int i = 0; i < 5; i++) executorService.execute(new ParallelJarRunner.JarRunnerTask(i + 1));
//        executorService.shutdown();
//    }

}
