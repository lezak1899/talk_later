package edu.lingnan.talklater.api.user.controller;


import edu.lingnan.talklater.api.user.domain.request.QueryEntity;
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
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Description:
 * date: 2020/12/22 20:58
 * author likunzhu
 * version
 * since JDK 1.8
 */

@Api(value="用户信息模块",tags={"提供用户数据接口"})
@RestController
@Slf4j
@RequestMapping("/api/user")
public class ApiUserController {

    @Autowired
    private UserXxService userXxService;

    @ApiOperation(value = "登陆接口")
    @ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 200, message = "成功!"),
            @io.swagger.annotations.ApiResponse(code = 11, message = "登录账号不存在！"),
    })
    @RequestMapping(method = RequestMethod.POST, value = "/login")
    public ApiResponse login(@RequestBody UserXxRequestDTO userXxRequestDTO, HttpSession session){
        Map result = new HashMap();
        String username = userXxRequestDTO.getUsername();
        String  password = userXxRequestDTO.getPassword();
        String  lastLoginEquipment = userXxRequestDTO.getLastLoginEquipment();

        //判断登录名是否存在
        if( !userXxService.isExistByUsername(username)) return ApiResponse.fail(ReturnCode.USER_NOTFOUND.getCode(),ReturnCode.USER_NOTFOUND.getMsg());

        //当前登录用户
        UserXx currentUser;
        UserXx userXx = new UserXx();
        userXx.setUsername(username);
        userXx.setPassword(password);
        userXx.setLastLoginEquipment(lastLoginEquipment);
        currentUser= userXxService.login(userXx);
        if(currentUser==null) return ApiResponse.fail(ReturnCode.USER_PASSWOED_ERROR.getCode(),ReturnCode.USER_PASSWOED_ERROR.getMsg());
        result.put("currentUser",currentUser);

        //session缓存登录对象
        session.setAttribute("currentuser",currentUser);

        return ApiResponse.success(result);
    }

    @ApiOperation(value = "修改用户信息接口")
    @ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 200, message = "成功!"),
    })
    @RequestMapping(method = RequestMethod.POST, value = "/modify/{zdmc}")
    public ApiResponse modifyUserXxByZdmc(@RequestBody UserXxRequestDTO userXxRequestDTO ,@PathVariable String zdmc){
        if(StringUtil.isNullOrEmpty(zdmc)||userXxRequestDTO==null)  return ApiResponse.fail();

        Map result = new HashMap();
        UserXx userXx= UserXxMapper.userXxRequestDTOToUserXx(userXxRequestDTO);
            Boolean flag= userXxService.modifyUserXxByZdmc(userXx,zdmc);
            if(!flag) return ApiResponse.fail();

        UserXx currentUser= userXxService.queryOne(userXx);
        result.put("currentUser",currentUser);
        return ApiResponse.success(result);

    }

    @ApiOperation(value = "注册接口")
    @ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 200, message = "成功!"),
    })
    @RequestMapping(method = RequestMethod.POST, value = "/register")
    public ApiResponse register(@RequestBody UserXxRequestDTO userXxRequestDTO){
        UserXx userXx= UserXxMapper.userXxRequestDTOToUserXx(userXxRequestDTO);
        int code = userXxService.register(userXx);
        if(code!=ReturnCode.SUCCESS.getCode()){
            return ApiResponse.fail(code,ReturnCode.getMsg(code));
        }
        return ApiResponse.success();
    }


    /**
     * 查询好友
     */
    @ApiOperation(value = "搜索接口")
    @ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 200, message = "成功!"),
    })
    @RequestMapping(method = RequestMethod.POST, value = "/search")
    public ApiResponse search(@RequestParam String friendUsername){

        UserXx userXx = new UserXx();
        userXx.setUsername(friendUsername);

        UserXx returnUserXx = userXxService.queryOne(userXx);

        if(returnUserXx==null) return ApiResponse.fail(ReturnCode.FRIEDN_NOTFOUND);

        Map result = new HashMap();
        result.put("username",returnUserXx.getUsername());
        result.put("nickname",returnUserXx.getNickname());
        result.put("faceImg",returnUserXx.getFaceImg());
        result.put("funSignature",returnUserXx.getFunSignature());

        return ApiResponse.success(result);
    }

    /**
     * 分页查询用户列表
     */
    @ApiOperation(value = "分页查询用户列表")
    @ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 200, message = "成功!"),
    })
    @RequestMapping(method = RequestMethod.POST, value = "/queryUserPage")
    public ApiResponse queryUserPage(@RequestBody QueryEntity queryEntity){
        if(StringUtil.isNullOrEmpty(queryEntity.getUserType()))
            return ApiResponse.fail(ReturnCode.PARAM_NULL);

        Map<String,Object> data = new HashMap<>();

        Page<UserXx> pageUserXx= userXxService.queryUserPage(queryEntity);

        data.put("pageUserXx",pageUserXx);

        return ApiResponse.success(data);
    }

    /**
     * 修改单个属性值
     */
    @ApiOperation(value = "修改单个属性值")
    @ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 200, message = "成功!"),
    })
    @RequestMapping(method = RequestMethod.GET, value = "/modifyByZdmc")
    public ApiResponse modifyByZdmc(@RequestParam String userId,@RequestParam String zdmc ,@RequestParam String value){
        if(StringUtil.isNullOrEmpty(userId) || StringUtil.isNullOrEmpty(zdmc)|| StringUtil.isNullOrEmpty(value))
            return ApiResponse.fail(ReturnCode.PARAM_NULL);

        Map<String,Object> data = new HashMap<>();

        UserXx userXx = userXxService.modifyByZdmc(userId,zdmc,value);

        if(userXx==null) return ApiResponse.fail();

        data.put("currentUser",userXx);

        return ApiResponse.success(data);
    }

    @ApiOperation(value = "录入用户接口")
    @ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 200, message = "成功!"),
    })
    @RequestMapping(method = RequestMethod.POST, value = "/addUser")
    public ApiResponse addUser(@RequestBody UserXxRequestDTO userXxRequestDTO){
        UserXx userXx= UserXxMapper.userXxRequestDTOToUserXx(userXxRequestDTO);
        int code = userXxService.register(userXx);
        if(code!=ReturnCode.SUCCESS.getCode()){
            return ApiResponse.fail(code,ReturnCode.getMsg(code));
        }
        return ApiResponse.success();
    }


    @ApiOperation(value = "修改用户信息接口")
    @ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 200, message = "成功!"),
    })
    @RequestMapping(method = RequestMethod.POST, value = "/modifyUser")
    public ApiResponse modifyUser(@RequestBody UserXxRequestDTO userXxRequestDTO){
        UserXx userXx= UserXxMapper.userXxRequestDTOToUserXx(userXxRequestDTO);

        userXxService.modifyUsesr(userXx);

        return ApiResponse.success();
    }

}
