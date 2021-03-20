package edu.lingnan.talklater.modules.stat.service.impl;

import edu.lingnan.talklater.api.user.domain.request.UserQueryEntity;
import edu.lingnan.talklater.modules.stat.domain.StatXx;
import edu.lingnan.talklater.modules.stat.domain.request.StatQueryEntity;
import edu.lingnan.talklater.modules.stat.repository.StatRepository;
import edu.lingnan.talklater.modules.stat.service.StatService;
import edu.lingnan.talklater.modules.user.domain.UserXx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Description:
 * date: 2021/3/16 13:59
 *
 * @author likunzhu
 * @since
 */
@Service
public class StatServiceImpl implements StatService {

    @Autowired
    private StatRepository statRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public Page<StatXx> queryStatPage(StatQueryEntity queryEntity) {

        //定义过滤模板的实体类
        StatXx statXx = new StatXx();

        //过滤条件
        if(queryEntity.getType()!=""){
            statXx.setType(queryEntity.getType());
        }
        if(queryEntity.getName()!=""){
            statXx.setName(queryEntity.getName());
        }

        //生成example
        ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreNullValues();
        Example<StatXx> example = Example.of(statXx, matcher);

        //分页，并且通过created_date字段进行降序排序
        PageRequest of = PageRequest.of(queryEntity.getPageNum()-1, queryEntity.getPageSize(), Sort.Direction.DESC, "excuteDate");

        Page<StatXx> statXxPage = statRepository.findAll(example,of);

        return statXxPage;
    }

    /**
     * 获得头部统计数据
     */
    @Override
    public Map<String,Object> queryHeadStatData(){
        StringBuffer sql = new StringBuffer();
        sql.append(" select ");
        sql.append(" (select total from s_stat_xx ssx  where `type` ='1' and name = '用户总量' order by excute_date desc limit 1) as yhzl,");
        sql.append(" (select total from s_stat_xx ssx  where `type` ='2' order by excute_date desc limit 1) as rxxl,");
        sql.append(" (select total from s_stat_xx ssx  where `type` ='3' order by excute_date desc limit 1) as rhyd,");
        sql.append(" (select total from s_stat_xx ssx  where `type` ='4' order by excute_date desc limit 1) as ryhzz");
        return jdbcTemplate.queryForMap(sql.toString());
    };

    /**
     * 获得用户总量7天内的数据
     */
    @Override
    public List<Map<String,Object>> queryYhzlData(){
        StringBuffer sql = new StringBuffer();
        sql.append(" select total,date_format( excute_date , '%Y%m' ) as date  from s_stat_xx ssx ");
        sql.append(" where `type` ='1' and name = '用户总量' order by excute_date desc limit 7");
        return jdbcTemplate.queryForList(sql.toString());

    };

    /**
     * 获得7天内的日信息量
     */
    @Override
    public List<Map<String,Object>> queryRxxlData(){
        StringBuffer sql = new StringBuffer();
        sql.append(" select total,date_format( excute_date , '%Y%m' ) as date  from s_stat_xx ssx ");
        sql.append(" where `type` ='4'  order by excute_date desc limit 7");
        return jdbcTemplate.queryForList(sql.toString());
    };

    /**
     * 获得7天内用户活跃度统计数据
     */
    @Override
    public List<Map<String,Object>> queryYhhydData(){
        StringBuffer sql = new StringBuffer();
        sql.append(" select total,date_format( excute_date , '%Y%m' ) as date  from s_stat_xx ssx ");
        sql.append(" where `type` ='2' order by excute_date desc limit 7");
        return jdbcTemplate.queryForList(sql.toString());
    };

    /**
     * 获得7天内用户日增长
     */
    @Override
    public List<Map<String,Object>> queryRzzData(){
        StringBuffer sql = new StringBuffer();
        sql.append(" select total,date_format( excute_date , '%Y%m' ) as date  from s_stat_xx ssx ");
        sql.append(" where `type` ='2'  order by excute_date desc limit 7");
        return jdbcTemplate.queryForList(sql.toString());
    };

    /**
     * 获得男女用户量
     */
    public List<Map<String,Object>> queryGenterData(){
        StringBuffer sql = new StringBuffer();
        sql.append(" select total as 'value', name as 'name' from s_stat_xx ssx ");
        sql.append(" where name in('女用户总量','男用户总量') ");
        sql.append(" and `type` ='1' order by excute_date desc limit 2");
        return jdbcTemplate.queryForList(sql.toString());

    };
}
