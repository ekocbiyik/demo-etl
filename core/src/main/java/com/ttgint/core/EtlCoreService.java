package com.ttgint.core;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class EtlCoreService {

    public LocalDateTime getCurrentTime() {
        return LocalDateTime.now();
    }

}
