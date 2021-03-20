package edu.lingnan.talklater.modules.stat.controller;

import edu.lingnan.talklater.api.user.domain.request.UserQueryEntity;
import edu.lingnan.talklater.modules.stat.domain.StatXx;
import edu.lingnan.talklater.modules.stat.domain.request.StatQueryEntity;
import edu.lingnan.talklater.modules.stat.service.StatService;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description:
 * date: 2021/3/16 20:32
 *
 * @author likunzhu
 * @since
 */
@Api(value="统计信息模块",tags={"提供统计数据接口"})
@RestController
@Slf4j
@RequestMapping("/api/stat")
public class StatController {

    @Autowired
    private StatService statService;

    /**
     * 分页查询统计信息列表
     */
    @ApiOperation(value = "分页查询统计信息列表")
    @ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 200, message = "成功!"),
    })
    @RequestMapping(method = RequestMethod.POST, value = "/queryStatPage")
    public ApiResponse queryStatPage(@RequestBody StatQueryEntity queryEntity){


        Map<String,Object> data = new HashMap<>();

        Page<StatXx> statXxPage= statService.queryStatPage(queryEntity);

        data.put("statXxPage",statXxPage);

        return ApiResponse.success(data);
    }

    /**
     * 查询欢迎面的统计信息
     */
    @ApiOperation(value = "查询头部统计信息")
    @ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 200, message = "成功!"),
    })
    @RequestMapping(method = RequestMethod.GET, value = "/queryHeadStatData")
    public ApiResponse queryHeadStatData(){

        Map<String,Object> data = new HashMap<>();

        Map<String,Object> statTotal= statService.queryHeadStatData();
        data.put("statTotal",statTotal);

        List<Map<String,Object>> yhzlList= statService.queryYhzlData();
        data.put("yhzlList",yhzlList);

        return ApiResponse.success(data);
    }


    /**
     * 统计页面echart数据
     */
    @ApiOperation(value = "统计页面echart数据")
    @ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 200, message = "成功!"),
    })
    @RequestMapping(method = RequestMethod.GET, value = "/queryStatDataForEchart")
    public ApiResponse queryStatDataForEchart(){

        Map<String,Object> data = new HashMap<>();
        

        //日信息量
        List<Map<String,Object>> rxxlList= statService.queryRxxlData();
        data.put("rxxlList",rxxlList);

        //男女比例
        List<Map<String,Object>> genterData= statService.queryGenterData();
        data.put("genterData",genterData);

        //日活跃度
        List<Map<String,Object>> yhhydList= statService.queryYhhydData();
        data.put("yhhydList",yhhydList);

        //日增长
        List<Map<String,Object>> rzzList = statService.queryRzzData();
        data.put("rzzList",rzzList);

        return ApiResponse.success(data);
    }

}
