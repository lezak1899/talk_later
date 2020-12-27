package edu.lingnan.talklater.modules.info.controller;

import edu.lingnan.talklater.modules.info.service.InfoService;
import edu.lingnan.talklater.modules.user.domain.UserXx;
import edu.lingnan.talklater.modules.user.service.UserXxService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * Description:
 * date: 2020/12/20 15:12
 * author likunzhu
 * version
 * since JDK 1.8
 */
@Api(value="服务器信息模块",tags={"用于返回服务器的一些信息"})
@RestController
@Slf4j
@RequestMapping("/info")
public class InfoController {

    @Autowired
    private InfoService infoService;

    @Autowired
    private UserXxService userXxService;


    @ApiOperation(value = "测试", notes = "返回一些测试信息", produces = "text/plain;charset=UTF-8")
    @GetMapping("/findAll")
    public List<UserXx> findAll(){
        return userXxService.findAll();
    }

    @ApiOperation(value = "测试", notes = "返回一些测试信息", produces = "text/plain;charset=UTF-8")
    @GetMapping("/test")
    public Map info(){

        Map<String,Object> result =infoService.test();

        log.error("logger--error-- 测试输出点什么！");
        log.warn("logger--warn-- 测试输出点什么！");

        return result;
    }
}
