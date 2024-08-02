package com.ttgint;

import com.ttgint.core.EtlCoreService;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class AggregationService {

    @Autowired
    private EtlCoreService etlCoreService;

    //    @Scheduled(fixedDelay = 5000)
    public void denemeTask() {
        log.info("{}", etlCoreService.getCurrentTime());
    }

    @PostConstruct
    public void test() throws InterruptedException {
        log.info("Benim PID: {}", ProcessHandle.current().pid());
        TimeUnit.SECONDS.sleep(3);
        System.exit(-1);
    }

}
