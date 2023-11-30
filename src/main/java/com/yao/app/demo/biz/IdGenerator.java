package com.yao.app.demo.biz;

import com.yao.app.demo.util.SnowFlakeIdGenerator;
import jakarta.annotation.PostConstruct;
import java.util.function.Supplier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class IdGenerator {

    private static final Logger LOG = LoggerFactory.getLogger(IdGenerator.class);

    public long generateUserId() {
        return supplier.get();
    }

    private Supplier<Long> supplier;

    @PostConstruct
    public void init() {
        LOG.warn("init snowflake");
        SnowFlakeIdGenerator snowFlakeIdGenerator = new SnowFlakeIdGenerator(1);
        supplier = () -> snowFlakeIdGenerator.nextId();
    }
}
