package edu.lingnan.talklater.modules.user.controller;


import edu.lingnan.talklater.modules.user.domain.UserXx;
import edu.lingnan.talklater.modules.user.domain.dto.request.UserXxRequestDTO;
import edu.lingnan.talklater.modules.user.domain.mapper.UserXxMapper;
import edu.lingnan.talklater.modules.user.service.UserXxService;
import edu.lingnan.talklater.response.ApiResponse;
import edu.lingnan.talklater.response.ReturnCode;
import io.netty.util.internal.StringUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

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

    @ApiOperation(value = "登陆接口")
    @ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 200, message = "成功!"),
            @io.swagger.annotations.ApiResponse(code = 11, message = "登录账号不存在！"),
    })
    @RequestMapping(method = RequestMethod.POST, value = "/login")
    public ApiResponse login(@RequestBody UserXxRequestDTO userXxRequestDTO){
        String username = userXxRequestDTO.getUsername();
        String  password = userXxRequestDTO.getPassword();

        Map result = new HashMap();
        //当前登录用户
        UserXx currentUser;
        UserXx userXx = new UserXx();
        userXx.setUsername(username);
         //判断登录名是否存在
        if( !userXxService.isExist(userXx)) return ApiResponse.fail(ReturnCode.USER_NOTFOUND.getCode(),ReturnCode.USER_NOTFOUND.getMsg());

        userXx.setPassword(password);
        currentUser= userXxService.queryOne(userXx);

        if(currentUser==null) return ApiResponse.fail(ReturnCode.USER_PASSWOED_ERROR.getCode(),ReturnCode.USER_PASSWOED_ERROR.getMsg());
        result.put("currentUser",currentUser);

        return ApiResponse.success(result);
    }

    @ApiOperation(value = "修改用户信息接口")
    @ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 200, message = "成功!"),
    })
    @RequestMapping(method = RequestMethod.POST, value = "/modify/{zdmc}")
    public ApiResponse modifyUserXx(@RequestBody UserXxRequestDTO userXxRequestDTO ,@PathVariable String zdmc){
        if(StringUtil.isNullOrEmpty(zdmc)||userXxRequestDTO==null)  return ApiResponse.fail();

        UserXx userXx= UserXxMapper.userXxRequestDTOToUserXx(userXxRequestDTO);



    }

    @ApiOperation(value = "注册接口")
    @ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 200, message = "成功!"),
    })
    @RequestMapping(method = RequestMethod.POST, value = "/register")
    public ApiResponse register(@RequestBody UserXxRequestDTO userXxRequestDTO){
        UserXx userXx= UserXxMapper.userXxRequestDTOToUserXx(userXxRequestDTO);
        boolean flag = userXxService.register(userXx);
        if(!flag){
            return ApiResponse.fail();
        }
        return ApiResponse.success();
    }
}
