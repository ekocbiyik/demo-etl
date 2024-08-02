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

    @Scheduled(fixedDelay = 5000)
    public void denemeTask() {
        log.info("{}", etlCoreService.getCurrentTime());
    }

}
