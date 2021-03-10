package edu.lingnan.talklater.api.menu.controller;

import edu.lingnan.talklater.api.menu.service.MenuService;
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
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
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
}
