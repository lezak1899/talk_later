package edu.lingnan.talklater.modules.stat.service;

import edu.lingnan.talklater.modules.stat.domain.StatXx;
import edu.lingnan.talklater.modules.stat.domain.request.StatQueryEntity;

import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

/**
 * Description:
 * date: 2021/3/16 13:59
 *
 * @author likunzhu
 * @since
 */
public interface StatService {

    /**
     * 分页查询数据
     * @param queryEntity
     * @return
     */
    public Page<StatXx> queryStatPage(StatQueryEntity queryEntity);

    /**
     * 获得头部统计数据
     */
    public Map<String,Object> queryHeadStatData();

    /**
     * 获得用户总量7天内的数据
     */
    public List<Map<String,Object>> queryYhzlData();

    /**
     * 获得7天内的日信息量
     */
    public List<Map<String,Object>> queryRxxlData();

    /**
     * 获得7天内用户活跃度统计数据
     */
    public List<Map<String,Object>> queryYhhydData();

    /**
     * 获得7天内用户日增长
     */
    public List<Map<String,Object>> queryRzzData();


    /**
     * 获得男女用户量
     */
    public List<Map<String,Object>> queryGenterData();
}
