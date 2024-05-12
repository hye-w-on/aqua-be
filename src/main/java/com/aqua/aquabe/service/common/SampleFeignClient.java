package com.aqua.aquabe.service.common;

import feign.Headers;
import java.util.HashMap;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "SampleFeignClient", url = "https://jsonplaceholder.typicode.com")
public interface SampleFeignClient {
  @Headers("Content-Type: application/json")
  @GetMapping(value = "/todos/{no}")
  HashMap<String, String> getTodos(@PathVariable("no") int no);
}
