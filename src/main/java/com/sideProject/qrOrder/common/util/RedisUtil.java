package com.sideproject.qrOrder.common.util;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RedisUtil {

    private final RedisTemplate<String, String> redisTemplate;

    public void setData(String key, String value, Long expire) {
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        deleteData(key);
        valueOperations.set(key, value, expire);
    }

    public String getData(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public void deleteData(String key) {
        redisTemplate.delete(key);
    }
}
