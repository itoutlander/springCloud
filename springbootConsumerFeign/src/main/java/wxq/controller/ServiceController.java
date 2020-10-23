package wxq.controller;

import common.entity.RestfulResult;
import common.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wxq.client.ServiceFeignClient;
import wxq.entity.ServiceInfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value = "consumerService")
public class ServiceController {

    @Autowired
    ServiceFeignClient serviceFeignClient;

    // 调用：localhost:6004/consumerService?token=1
    @RequestMapping("/hello")
    public void consumerService(HttpServletRequest request, HttpServletResponse response,
                                @Validated ServiceInfo serviceInfo){

        RestfulResult restfulResult =  serviceFeignClient.hello(serviceInfo);

        CommonUtils.printDataJason(response, restfulResult);
    }
}
