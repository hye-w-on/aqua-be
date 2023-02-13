package com.aqua.aquabe.service.common;

import com.aqua.aquabe.model.session.SessionVO;

import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RedisSessionService {
    @Value(value = "${redis.session-ttl}")
    private int redisSessionTtl;

    private final RedisTemplate<String, SessionVO> redisSessionTemplate;

    @Resource(name = "redisSessionTemplate")
    ValueOperations<String, SessionVO> valueOperations;

    public void createSession(SessionVO session) {

        String key = session.getSessionId();
        valueOperations.set(key, session);

        redisSessionTemplate.expire(key, redisSessionTtl, TimeUnit.SECONDS);
    }

    public SessionVO getSession(String key) {
        return valueOperations.get(key);
    }

    public Boolean deleteSession(String key) {
        return redisSessionTemplate.delete(key);
    }
}
