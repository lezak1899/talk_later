//package edu.lingnan.controller;
//
//
//import edu.lingnan.response.ApiResponse;
//
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import io.swagger.annotations.ApiResponses;
//
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//
///**
// * Description:
// * date: 2020/12/22 20:58
// * author likunzhu
// * version
// * since JDK 1.8
// */
//
//@Api(value="附件模块",tags={"附件上传下载接口"})
//@RestController
//@RequestMapping("/user")
//public class UserController {
//
//
//
//
//
//
//    @ApiOperation(value = "登陆接口")
//    @ApiResponses(value = {
//            @io.swagger.annotations.ApiResponse(code = 200, message = "成功!"),
//            @io.swagger.annotations.ApiResponse(code = 11, message = "登录账号不存在！"),
//    })
//    @RequestMapping(method = RequestMethod.POST, value = "/login")
//    public ApiResponse login(){
//
////        if(!uUserXxService.findUserIsExist(user.getUsername()))
////            return ApiResponse.fail(ReturnCode.USER_NOTFOUND.getCode(),ReturnCode.USER_NOTFOUND.getMsg());
//
//        return ApiResponse.success();
//    }
//}
