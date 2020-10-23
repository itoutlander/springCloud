package wxq.controller;

import common.entity.RestfulResult;
import common.utils.CommonUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wxq.entity.ServiceInfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value = "service")
public class ServiceController {

    @PostMapping(value = "/hello")
    public void login(HttpServletRequest request, HttpServletResponse response,
                      @Validated ServiceInfo serviceInfo) {

        RestfulResult restfulResult = new RestfulResult();

        try {
            restfulResult.setData("Service2:Welcome " + serviceInfo.getName() + "!");

        } catch (Exception e) {
            e.printStackTrace();
        }

        CommonUtils.printDataJason(response, restfulResult);
    }

    @RequestMapping(value = "rest")
    public String rest(@Validated ServiceInfo serviceInfo){

        return "Service2:Welcome " + serviceInfo.getName() + " !";
    }
}
