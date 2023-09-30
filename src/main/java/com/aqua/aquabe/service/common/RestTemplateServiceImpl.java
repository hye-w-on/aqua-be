package com.aqua.aquabe.service.common;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.aqua.aquabe.model.common.SampleRequestVO;
import com.aqua.aquabe.model.common.SampleResponseVO;

@Service
@RequiredArgsConstructor
public class RestTemplateServiceImpl implements RestTemplateService {

    private final RestTemplate restTemplate; // Bean

    @Override
    public SampleResponseVO getSample(SampleRequestVO sampleRequestVO) {

        String url = "";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String body = "{}"; // gson.toJson(sampleRequestVO);

        HttpEntity<String> requestEntity = new HttpEntity<String>(body, headers);

        ResponseEntity<SampleResponseVO> response = restTemplate.exchange(
                url, HttpMethod.GET, requestEntity, SampleResponseVO.class);

        SampleResponseVO result = response.getBody();

        return result;
    }
}
