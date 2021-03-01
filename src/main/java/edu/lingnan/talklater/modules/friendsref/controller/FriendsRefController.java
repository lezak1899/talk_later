package edu.lingnan.talklater.modules.friendsref.controller;

import edu.lingnan.talklater.modules.friendsref.domain.vo.FriendsRefVo;
import edu.lingnan.talklater.modules.friendsref.service.FriendsRefService;
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
import java.util.List;

/**
 * Description:
 * date: 2021/2/4 16:00
 *
 * @author likunzhu
 * @since
 */
@Api(value="好友列表模块",tags={"提供好友列表数据接口"})
@RestController
@Slf4j
@RequestMapping("/friendref")
public class FriendsRefController {

    @Autowired
    private FriendsRefService friendsRefService;


    @ApiOperation(value = "查询当前用户的好友列表")
    @ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 200, message = "成功!"),
    })
    @RequestMapping(method = RequestMethod.POST, value = "/queryFriendList/{username}")
    public ApiResponse queryFriendList(@PathVariable String username){

        HashMap data = new HashMap();

        if(StringUtil.isNullOrEmpty(username)) return ApiResponse.fail(ReturnCode.PARAM_NULL);

        List<FriendsRefVo> friendsRefVoList = friendsRefService.queryFriendList(username);

        data.put("friendsRefVoList",friendsRefVoList);

        return ApiResponse.success(data);
    }

    @ApiOperation(value = "删除好友")
    @ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 200, message = "成功!"),
    })
    @RequestMapping(method = RequestMethod.POST, value = "/delFriend/{myUsername}/{friendUsername}")
    public ApiResponse delFriend(@PathVariable("myUsername") String myUsername,@PathVariable("friendUsername") String friendUsername){



        if(StringUtil.isNullOrEmpty(myUsername)||StringUtil.isNullOrEmpty(friendUsername)) return ApiResponse.fail(ReturnCode.PARAM_NULL);


        int result =  friendsRefService.delFriend(myUsername,friendUsername);

        if (result!=2){
            ApiResponse.fail();
        }

        return ApiResponse.success();
    }



}
