package edu.lingnan.talklater.modules.requestxx.controller;

import edu.lingnan.talklater.modules.requestxx.domain.RequestXx;
import edu.lingnan.talklater.modules.requestxx.service.RequestXxService;
import edu.lingnan.talklater.response.ApiResponse;
import edu.lingnan.talklater.response.ReturnCode;
import io.netty.util.internal.StringUtil;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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


    @RequestMapping("/sendRequest")
    public ApiResponse search(@RequestParam("senderUsername") String senderUsername ,@RequestParam("recipientUsername") String recipientUsername){

        //校验
        if(StringUtil.isNullOrEmpty(recipientUsername)) return ApiResponse.fail(ReturnCode.FRIEND_USERNAME_NULL);
        int code = requestXxService.verifyRequest(senderUsername,recipientUsername);
        if(code!=200) return ApiResponse.fail(code,ReturnCode.getMsg(code));




        return null;
    }



}
