package edu.lingnan.talklater.api.menu.controller;

import edu.lingnan.talklater.api.menu.domain.MenuXx;
import edu.lingnan.talklater.api.menu.domain.request.MenuQueryEntity;
import edu.lingnan.talklater.api.menu.domain.response.MenuOption;
import edu.lingnan.talklater.api.menu.domain.response.RoleMenu;
import edu.lingnan.talklater.api.menu.service.MenuService;
import edu.lingnan.talklater.api.role.domiain.Request.RoleQueryEntity;
import edu.lingnan.talklater.api.role.domiain.RoleXx;
import edu.lingnan.talklater.modules.user.domain.UserXx;
import edu.lingnan.talklater.modules.user.domain.dto.request.UserXxRequestDTO;
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
import java.util.List;
import java.util.Map;

/**
 * Description:
 * date: 2021/3/8 11:06
 *
 * @author likunzhu
 * @since
 */
@Api(value="菜单模块",tags={"提供菜单的数据"})
@RestController
@Slf4j
@RequestMapping("/api/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @ApiOperation(value = "查询用户的所有菜单信息")
    @ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 200, message = "成功!"),
    })
    @RequestMapping(method = RequestMethod.POST, value = "/getMenuList/{userId}")
    public ApiResponse getMenuList(@PathVariable String userId){

        if(StringUtil.isNullOrEmpty(userId)){ return ApiResponse.fail(ReturnCode.PARAM_NULL);}

        Map data = new HashMap();
        data.put("menuList",menuService.getMenuList(userId));

        return ApiResponse.success(data);
    }

    /**
     * 分页查询菜单列表
     */
    @ApiOperation(value = "分页查询菜单列表")
    @ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 200, message = "成功!"),
    })
    @RequestMapping(method = RequestMethod.POST, value = "/queryMenuPage")
    public ApiResponse queryMenuPage(@RequestBody MenuQueryEntity queryEntity){

        Map<String,Object> data = new HashMap<>();

        Page<MenuXx> pageMenuXx= menuService.queryMenuPage(queryEntity);

        data.put("pageMenuXx",pageMenuXx);

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
    public ApiResponse modifyByZdmc(@RequestParam String menuId, @RequestParam String zdmc , @RequestParam String value){
        if(StringUtil.isNullOrEmpty(menuId) || StringUtil.isNullOrEmpty(zdmc)|| StringUtil.isNullOrEmpty(value))
            return ApiResponse.fail(ReturnCode.PARAM_NULL);

        MenuXx menuxx = menuService.modifyByZdmc(menuId,zdmc,value);

        if(menuxx==null) return ApiResponse.fail();

        return ApiResponse.success();
    }

    /**
     * 按角色id查询所有菜单信息
     */
    @ApiOperation(value = "按角色id查询所有菜单信息")
    @ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 200, message = "成功!"),
    })
    @RequestMapping(method = RequestMethod.GET, value = "/queryMenuListByRoleId")
    public ApiResponse queryMenuListByRoleId(@RequestParam("roleId") String roleId){
        if(StringUtil.isNullOrEmpty(roleId)){ return ApiResponse.fail(ReturnCode.PARAM_NULL);}
        Map<String,Object> data = new HashMap<>();
        List<RoleMenu> roleMenuList = menuService.queryMenuListByRoleId(roleId);
        data.put("roleMenuList",roleMenuList);
        return ApiResponse.success(data);
    }

    /**
     * 查询菜单选项
     */
    @ApiOperation(value = "查询菜单选项")
    @ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 200, message = "成功!"),
    })
    @RequestMapping(method = RequestMethod.GET, value = "/queryMenuOptions")
    public ApiResponse queryMenuOptions(@RequestParam("fid") String fid)  {
        if(StringUtil.isNullOrEmpty(fid)){ return ApiResponse.fail(ReturnCode.PARAM_NULL);}
        Map<String,Object> data = new HashMap<>();
        List<MenuOption> menuOptions = menuService.queryMenuOptions(fid);

        data.put("menuOptions",menuOptions);
        return ApiResponse.success(data);
    }

    /**
     * 给角色分配菜单
     */
    @ApiOperation(value = "给角色分配菜单")
    @ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 200, message = "成功!"),
    })
    @RequestMapping(method = RequestMethod.GET, value = "/distributeMenu")
    public ApiResponse distributeMenu(@RequestParam("roleId") String roleId,@RequestParam("menuId1") String menuId1,@RequestParam("menuId2") String menuId2)  {
        if(StringUtil.isNullOrEmpty(roleId)){ return ApiResponse.fail(ReturnCode.PARAM_NULL);}

        int n =menuService.distributeMenu(roleId,menuId1,menuId2);

        if(n!=200) return ApiResponse.fail(n,ReturnCode.getMsg(n));

        return ApiResponse.success();
    }

    /**
     * 通过role_menu_id删除关联
     */
    @ApiOperation(value = "通过role_menu_id删除关联")
    @ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 200, message = "成功!"),
    })
    @RequestMapping(method = RequestMethod.GET, value = "/deleteCorrelation")
    public ApiResponse deleteCorrelation(@RequestParam("roleMenuId") String roleMenuId)  {
        if(StringUtil.isNullOrEmpty(roleMenuId)){ return ApiResponse.fail(ReturnCode.PARAM_NULL);}

        int n =menuService.deleteCorrelation(roleMenuId);

        if(n<1) return ApiResponse.fail();

        return ApiResponse.success();
    }
}
