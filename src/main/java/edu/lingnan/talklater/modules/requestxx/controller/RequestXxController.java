package edu.lingnan.talklater.modules.requestxx.controller;

import edu.lingnan.talklater.modules.friendsref.service.FriendsRefService;
import edu.lingnan.talklater.modules.requestxx.domain.FriendsRef;
import edu.lingnan.talklater.modules.requestxx.domain.RequestXx;
import edu.lingnan.talklater.modules.requestxx.domain.vo.RequestXxVo;
import edu.lingnan.talklater.modules.requestxx.service.RequestXxService;
import edu.lingnan.talklater.response.ApiResponse;
import edu.lingnan.talklater.response.ReturnCode;
import io.netty.util.internal.StringUtil;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description:
 * date: 2021/2/4 14:15
 *
 * @author likunzhu
 * @since
 */
@Api(value="好友请求模块",tags={"提供好友请求数据接口"})
@RestController
@Slf4j
@RequestMapping("/request")
public class RequestXxController {

    @Autowired
    private RequestXxService requestXxService;

    @Autowired
    private FriendsRefService friendsRefService;

    @RequestMapping("/sendRequest")
    public ApiResponse search(@RequestBody RequestXx requestXx){

        if(requestXx==null) return ApiResponse.fail(ReturnCode.PARAM_NULL);
        //校验
        if(StringUtil.isNullOrEmpty(requestXx.getRecipientUsername())) return ApiResponse.fail(ReturnCode.FRIEND_USERNAME_NULL);
        int code = requestXxService.verifyRequest(requestXx.getSenderUsername(),requestXx.getRecipientUsername());
        if(code!=200) return ApiResponse.fail(code,ReturnCode.getMsg(code));

        requestXxService.addRequest(requestXx.getSenderUsername(),requestXx.getRecipientUsername());

        return ApiResponse.success();
    }

    @RequestMapping("/queryAllRequest/{username}")
    public ApiResponse queryAllRequest(@PathVariable String username){
        Map result = new HashMap();
        if(StringUtil.isNullOrEmpty(username)) return ApiResponse.fail(ReturnCode.PARAM_NULL);

        List<RequestXxVo> requestList = requestXxService.queryAllRequest(username);
        result.put("requestList",requestList);
        return ApiResponse.success(result);
    }

    @RequestMapping("/handle/{requestId}/{actionType}")
    public ApiResponse handle(@PathVariable String  requestId,@PathVariable String  actionType){


        if(StringUtil.isNullOrEmpty(requestId)||StringUtil.isNullOrEmpty(actionType)) return ApiResponse.fail(ReturnCode.PARAM_NULL);

        boolean flag = requestXxService.handleRequest(requestId,actionType);

        if(!flag) return ApiResponse.fail();

        return ApiResponse.success();

    }

}
