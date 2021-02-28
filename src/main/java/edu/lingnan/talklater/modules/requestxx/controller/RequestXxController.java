package edu.lingnan.talklater.modules.requestxx.controller;

import edu.lingnan.talklater.modules.chat.Enum.MsgActionEnum;
import edu.lingnan.talklater.modules.chat.domain.DataContent;
import edu.lingnan.talklater.modules.chat.domain.UserChannelRel;
import edu.lingnan.talklater.modules.friendsref.service.FriendsRefService;
import edu.lingnan.talklater.modules.requestxx.domain.RequestXx;
import edu.lingnan.talklater.modules.requestxx.domain.vo.RequestXxVo;
import edu.lingnan.talklater.modules.requestxx.service.RequestXxService;
import edu.lingnan.talklater.response.ApiResponse;
import edu.lingnan.talklater.response.ReturnCode;
import edu.lingnan.talklater.utils.JsonUtils;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
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
    public ApiResponse sendRequest(@RequestBody RequestXx requestXx){

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

        //当好友请求发送者，发送的请求被通过时，重新拉取发送者的好友请求
        if("2".equals(actionType)){

            RequestXx requestXx = requestXxService.queryById(requestId);


            if(requestXx!=null){
                Channel sendChannel = UserChannelRel.get(requestXx.getSenderUsername());
                if (sendChannel != null) {
                    // 使用websocket主动推送消息到请求发起者，更新他的通讯录列表为最新
                    DataContent dataContent = new DataContent();
                    dataContent.setAction(MsgActionEnum.PULL_FRIEND.type);
                    sendChannel.writeAndFlush(
                            new TextWebSocketFrame(
                                    JsonUtils.objectToJson(dataContent)));
                }
            }


        }

        return ApiResponse.success();

    }

}
