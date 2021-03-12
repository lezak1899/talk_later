package edu.lingnan.talklater.api.role.controller;

import edu.lingnan.talklater.api.user.domain.request.UserQueryEntity;

import edu.lingnan.talklater.response.ApiResponse;
import edu.lingnan.talklater.response.ReturnCode;
import io.netty.util.internal.StringUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Description:
 * date: 2021/3/11 17:42
 *
 * @author likunzhu
 * @since
 */

@Api(value="角色信息模块",tags={"提供角色信息数据接口"})
@RestController
@Slf4j
@RequestMapping("/api/role")
public class RoleController {

    /**
     * 分页查询用户列表
     */
    @ApiOperation(value = "分页查询用户列表")
    @ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 200, message = "成功!"),
    })
    @RequestMapping(method = RequestMethod.POST, value = "/queryRolePage")
    public ApiResponse queryUserPage(@RequestBody UserQueryEntity queryEntity){
        if(StringUtil.isNullOrEmpty(queryEntity.getUserType()))
            return ApiResponse.fail(ReturnCode.PARAM_NULL);

        Map<String,Object> data = new HashMap<>();

//        Page<UserXx> pageUserXx= userXxService.queryUserPage(queryEntity);
//
//        data.put("pageUserXx",pageUserXx);

        return ApiResponse.success(data);
    }
}
