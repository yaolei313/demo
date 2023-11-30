package com.yao.app.demo.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * https://github.com/twitter-archive/snowflake/releases/tag/snowflake-2010 把dataCenterId和workerId合并到一起了
 */
public class SnowFlakeIdGenerator {

    private static final Logger LOG = LoggerFactory.getLogger(SnowFlakeIdGenerator.class);

    /**
     * 起始的时间戳(2023-01-01)
     */
    private final long twepoch = 1672531200000L;

    /**
     * 位数
     */
    private final int workerIdBits = 10;

    private final int sequenceBits = 12;

    /**
     * 最大值1024-1=1023
     */
    private final int maxWorkerId = -1 ^ (-1 << workerIdBits);

    /**
     * 位移
     */
    private final int workerIdShift = sequenceBits;

    private final int timestampLeftShift = sequenceBits + workerIdBits;

    /**
     * 最大值4096-1-4095
     */
    private final int sequenceMask = -1 ^ (-1 << sequenceBits);

    // ----

    private int workerId;

    private int sequence = 0;

    private long lastTimestamp = -1L;

    public SnowFlakeIdGenerator(int workerId) {
        if (workerId > maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException("worker Id can't be greater than maxWorkerId or less than 0");
        }
        LOG.info("worker starting. timestamp left shift {}, worker id bits {}, sequence bits {}, worker id {}",
            timestampLeftShift, workerIdBits, sequenceBits, workerId);
        this.workerId = workerId;
    }

    public synchronized long nextId() {
        long timestamp = timeGen();

        if (timestamp < lastTimestamp) {
            LOG.error("clock is moving backwards. Rejecting requests until {}.", lastTimestamp);
            throw new RuntimeException(String.format("Clock moved backwards. Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));
        }

        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) & sequenceMask;
            if (sequence == 0) {
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            sequence = 0;
        }

        lastTimestamp = timestamp;
        return ((timestamp - twepoch) << timestampLeftShift) |
            (workerId << workerIdShift) |
            sequence;

    }

    private long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    private long timeGen() {
        return System.currentTimeMillis();
    }
    
}
