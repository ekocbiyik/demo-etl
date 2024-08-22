package com.ttgint.core;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.AbstractDataSource;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Slf4j
@RequiredArgsConstructor
public class RetryableDataSource extends AbstractDataSource {

    private final DataSource dataSource;
    private int retryCount = 0;

    @Override
    @Retryable(maxAttempts = 6, backoff = @Backoff(delay = 4000))
    public Connection getConnection() throws SQLException {
        if (retryCount++ == 6) {
            log.error("contact with your dba!!");
            System.exit(-1);
        }
        log.info("try to connect db {}. time ", retryCount);
        return dataSource.getConnection();
    }

    @Override
    @Retryable(maxAttempts = 6, backoff = @Backoff(delay = 4000))
    public Connection getConnection(String username, String password) throws SQLException {
        if (retryCount++ == 6) {
            log.error("contact with your dba!!");
            System.exit(-1);
        }
        log.info("try to connect db with username {}. time ", retryCount);
        return dataSource.getConnection(username, password);
    }
}