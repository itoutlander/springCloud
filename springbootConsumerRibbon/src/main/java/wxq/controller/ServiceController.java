package wxq.controller;

import com.fasterxml.jackson.annotation.JacksonInject;
import com.netflix.discovery.converters.Auto;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import common.entity.MyRestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import wxq.entity.ServiceInfo;

@RestController
public class ServiceController {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${server.port}")
    String port;

    // 调用：localhost:4007/consumerServiceRibbon?token=1
    @RequestMapping("/consumerServiceRibbon")
    @HystrixCommand(fallbackMethod="consumerServiceRibbonFallback")
    public String consumerServiceRibbon(@Validated ServiceInfo serviceInfo){
        String result = this.restTemplate.postForObject("http://springbootService/service/rest", serviceInfo, String.class);
        return result;
    }

    public String consumerServiceRibbonFallback(@RequestBody ServiceInfo serviceInfo){
        return "consumerServiceRibbon异常，端口：" + port + "，Name=" + serviceInfo.getName();
    }
}
