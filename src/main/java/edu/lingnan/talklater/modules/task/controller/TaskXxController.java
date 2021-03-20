package edu.lingnan.talklater.modules.task.controller;

import edu.lingnan.talklater.modules.stat.domain.StatXx;
import edu.lingnan.talklater.modules.stat.domain.request.StatQueryEntity;
import edu.lingnan.talklater.modules.stat.service.StatService;
import edu.lingnan.talklater.modules.task.domain.TaskXx;
import edu.lingnan.talklater.modules.task.repository.request.TaskXxQueryEntity;
import edu.lingnan.talklater.modules.task.service.TaskXxService;
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
 * date: 2021/3/16 20:50
 *
 * @author likunzhu
 * @since
 */
@Api(value="统计信息模块",tags={"提供统计数据接口"})
@RestController
@Slf4j
@RequestMapping("/api/task")
public class TaskXxController {

    @Autowired
    private TaskXxService taskXxService;

    /**
     * 分页查询统计任务信息列表
     */
    @ApiOperation(value = "分页查询统计任务信息列表")
    @ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 200, message = "成功!"),
    })
    @RequestMapping(method = RequestMethod.POST, value = "/queryTaskPage")
    public ApiResponse queryTaskPage(@RequestBody TaskXxQueryEntity queryEntity){


        Map<String,Object> data = new HashMap<>();

        Page<TaskXx> taskXxPage= taskXxService.queryTaskPage(queryEntity);

        data.put("taskXxPage",taskXxPage);

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
    public ApiResponse modifyByZdmc(@RequestParam String taskId, @RequestParam String zdmc , @RequestParam String value){
        if(StringUtil.isNullOrEmpty(taskId) || StringUtil.isNullOrEmpty(zdmc)|| StringUtil.isNullOrEmpty(value))
            return ApiResponse.fail(ReturnCode.PARAM_NULL);

        TaskXx taskXx = taskXxService.modifyByZdmc(taskId,zdmc,value);

        if(taskXx==null) return ApiResponse.fail();

        return ApiResponse.success();
    }

    /**
     * 修改任务属性
     */
    @ApiOperation(value = "修改任务属性")
    @ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 200, message = "成功!"),
    })
    @RequestMapping(method = RequestMethod.POST, value = "/modify")
    public ApiResponse modify(@RequestBody TaskXx taskXx){
        if(StringUtil.isNullOrEmpty(taskXx.getId()))
            return ApiResponse.fail(ReturnCode.PARAM_NULL);

        int n = taskXxService.modify(taskXx);

        if(n<1) return ApiResponse.fail();

        return ApiResponse.success();
    }

}
