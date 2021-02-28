package edu.lingnan.talklater.modules.msg.controller;

import edu.lingnan.talklater.modules.msg.domain.MsgXx;
import edu.lingnan.talklater.modules.msg.service.MsgService;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description:
 * date: 2021/2/20 14:00
 *
 * @author likunzhu
 * @since
 */
@Api(value="好友列表模块",tags={"提供好友列表数据接口"})
@RestController
@Slf4j
@RequestMapping("/msg")
public class MsgController {

    @Autowired
    private MsgService msgService;


    @ApiOperation(value = "查询未签收的消息")
    @ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 200, message = "成功!"),
    })
    @RequestMapping(method = RequestMethod.POST, value = "/queryMsg/{recipientUsername}")
    public ApiResponse queryMsg(@PathVariable("recipientUsername") String recipientUsername){
        if(StringUtil.isNullOrEmpty(recipientUsername)) return  ApiResponse.fail(ReturnCode.PARAM_NULL);
        Map data = new HashMap();
        List<MsgXx> msgXxList = msgService.queryMsg(recipientUsername);
        data.put("msgXxList",msgXxList);
        return ApiResponse.success(data);
    }

}
