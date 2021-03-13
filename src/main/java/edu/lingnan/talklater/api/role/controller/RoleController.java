package edu.lingnan.talklater.api.role.controller;

import edu.lingnan.talklater.api.role.domiain.Request.RoleQueryEntity;
import edu.lingnan.talklater.api.role.domiain.RoleXx;
import edu.lingnan.talklater.api.role.service.RoleService;
import edu.lingnan.talklater.api.user.domain.request.UserQueryEntity;

import edu.lingnan.talklater.modules.user.domain.UserXx;
import edu.lingnan.talklater.request.QueryEntity;
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

    @Autowired
    private RoleService roleService;

    /**
     * 分页查询角色列表
     */
    @ApiOperation(value = "分页查询角色列表")
    @ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 200, message = "成功!"),
    })
    @RequestMapping(method = RequestMethod.POST, value = "/queryRolePage")
    public ApiResponse queryRolePage(@RequestBody RoleQueryEntity queryEntity){

        Map<String,Object> data = new HashMap<>();

        Page<RoleXx> pageRoleXx= roleService.queryRolePage(queryEntity);

        data.put("pageRoleXx",pageRoleXx);

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
    public ApiResponse modifyByZdmc(@RequestParam String roleId, @RequestParam String zdmc , @RequestParam String value){
        if(StringUtil.isNullOrEmpty(roleId) || StringUtil.isNullOrEmpty(zdmc)|| StringUtil.isNullOrEmpty(value))
            return ApiResponse.fail(ReturnCode.PARAM_NULL);

        RoleXx rolexx = roleService.modifyByZdmc(roleId,zdmc,value);

        if(rolexx==null) return ApiResponse.fail();

        return ApiResponse.success();
    }
}
