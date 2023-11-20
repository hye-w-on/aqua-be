package com.aqua.aquabe.service.common;

import com.aqua.aquabe.model.session.MemberSessionVO;

import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;

import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RedisSessionService {

    private final RedisTemplate<String, MemberSessionVO> redisSessionTemplate;

    @Resource(name = "redisSessionTemplate")
    ValueOperations<String, MemberSessionVO> valueOperations;

    public void createSession(MemberSessionVO session) {

        String key = session.getRedisSessionId();
        valueOperations.set(key, session);

        redisSessionTemplate.expire(key, 1800, TimeUnit.SECONDS);
    }

    public MemberSessionVO getMemberSession(String key) {
        return valueOperations.get(key);
    }

    public Boolean deleteSession(String key) {
        return redisSessionTemplate.delete(key);
    }
}
