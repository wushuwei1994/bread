package com.carrywei.breadspring.controller;

import com.carrywei.breadspring.inout.CommonResp;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @Author wushuwei
 * @Description
 * @Date 2021/8/11
 **/
@RestController
public class TestController {

    @GetMapping("test")
    public CommonResp test() {
        CommonResp commonResp = new CommonResp();
        commonResp.setDate(new Date());
        return commonResp;
    }

    public static void main(String[] args) throws JsonProcessingException {
        CommonResp commonResp = new CommonResp();
        commonResp.setDate(new Date());
        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println(objectMapper.writeValueAsString(commonResp));

    }
}
