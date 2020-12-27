package edu.lingnan.talklater.modules.user.controller;


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

/**
 * Description:
 * date: 2020/12/22 20:58
 * author likunzhu
 * version
 * since JDK 1.8
 */

@Api(value="服务器信息模块",tags={"用于返回服务器的一些信息"})
@RestController
@Slf4j
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserXxService userXxService;


    @ApiOperation(value = "测试", notes = "返回一些测试信息", produces = "text/plain;charset=UTF-8")
    @GetMapping("/findAll")
    public List<UserXx> findAll(){
        return userXxService.findAll();
    }

//    @ApiOperation(value = "登陆接口")
//    @ApiResponses(value = {
//            @io.swagger.annotations.ApiResponse(code = 200, message = "成功!"),
//            @io.swagger.annotations.ApiResponse(code = 11, message = "登录账号不存在！"),
//    })
//    @RequestMapping(method = RequestMethod.POST, value = "/login")
//    public List<UserXx> login(){
//
////        if(!uUserXxService.findUserIsExist(user.getUsername()))
////            return ApiResponse.fail(ReturnCode.USER_NOTFOUND.getCode(),ReturnCode.USER_NOTFOUND.getMsg());
//
//        return userXxService.findAll();
//    }
}
