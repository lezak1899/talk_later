package edu.lingnan.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description:
 * date: 2020/12/20 15:12
 * author likunzhu
 * version
 * since JDK 1.8
 */
@RestController
@Slf4j
public class TestController {

    @GetMapping("/info")
    public String info(){

        log.error("logger--error-- 测试输出点什么！");
        log.warn("logger--warn-- 测试输出点什么！");

        return "some test info！";
    }
}
