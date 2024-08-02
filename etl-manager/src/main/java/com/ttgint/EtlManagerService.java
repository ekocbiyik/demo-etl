package com.ttgint;

import com.ttgint.core.EtlCoreService;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
@Service
public class EtlManagerService {

    @Autowired
    private EtlCoreService etlCoreService;

    @Scheduled(fixedDelay = 5000)
    public void denemeTask() {
        log.info("{}", etlCoreService.getCurrentTime());
    }

    @PostConstruct
    public void test() {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++) executorService.execute(new ParallelJarRunner.JarRunnerTask(i + 1));
        executorService.shutdown();
    }

}
