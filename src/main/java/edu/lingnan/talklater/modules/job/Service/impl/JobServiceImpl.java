package edu.lingnan.talklater.modules.job.Service.impl;

import edu.lingnan.talklater.modules.job.Service.JobService;
import edu.lingnan.talklater.modules.stat.domain.StatXx;
import edu.lingnan.talklater.modules.stat.repository.StatRepository;

import edu.lingnan.talklater.modules.task.taskenum.TaskEnum;
import edu.lingnan.talklater.utils.SpringBeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.Types;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Description:
 * date: 2021/3/16 11:41
 *
 * @author likunzhu
 * @since
 */
@Service
public class JobServiceImpl implements JobService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private StatRepository statRepository;

    //更新被执行的时间
    private void updateExcuteTime(String jobId) {
        StringBuffer sql = new StringBuffer();
        sql.append(" update s_task_xx set last_excute_time = now() where id = ?");
        JdbcTemplate jdbcTemplate = (JdbcTemplate) SpringBeanUtil.getBean("jdbcTemplate");
        jdbcTemplate.update(sql.toString(),new Object[]{jobId},new int[]{Types.VARCHAR});
    }

    /**
     * 统计用户总量，男女用户数量
     */
    @Override
    public void StatUserNum() {
        StringBuffer sql = new StringBuffer();
        sql.append(" select count(1) as sum ,sex from u_user_xx uux");
        sql.append(" where is_valid ='1'");
        sql.append(" and usertype ='1'");
        sql.append(" and to_days( now() )-to_days(date_format( FROM_UNIXTIME(created_date /1000,'%Y-%m-%d %H:%i:%s'), '%Y%m%d')) >=1");
        sql.append(" group by sex");
        List<Map<String,Object>> res = jdbcTemplate.queryForList(sql.toString());

        int sum=0;

        for(Map<String,Object> item:res){
            int itemNum = Integer.parseInt(String.valueOf(item.get("sum")));
            String itemSex = String.valueOf(item.get("sex"));
            StatXx statXx = new StatXx();
            statXx.setName(itemSex+"用户总量");
            statXx.setTotal(itemNum);
            statXx.setCycle("day");

            //由于统计获得的为昨天天的数据，所以这里获得昨天的日期
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            calendar.add(Calendar.DATE, -1);
            statXx.setStatDate(calendar.getTime());

            statXx.setType("1");
            statXx.setValid("1");
            statXx.setCreatedDate(System.currentTimeMillis());
            statRepository.saveAndFlush(statXx);
            sum+=itemNum;
        }

        StatXx statXx = new StatXx();
        statXx.setName("用户总量");
        statXx.setTotal(sum);
        statXx.setCycle("day");

        //由于统计获得的为昨天天的数据，所以这里获得昨天的日期
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE, -1);
        statXx.setStatDate(calendar.getTime());

        statXx.setType("1");
        statXx.setValid("1");
        statXx.setCreatedDate(System.currentTimeMillis());
        statRepository.saveAndFlush(statXx);

        this.updateExcuteTime(TaskEnum.用户总量统计.getId());
    }

    /**
     * 用户活跃度统计，统计前一天登录的用户
     */
    @Override
    public void StatActiveUserRate(){


        StringBuffer sql = new StringBuffer();
        sql.append(" select count(1) as sum from u_user_xx uux where is_valid ='1' ");
        sql.append(" and to_days( now() )-to_days(date_format( FROM_UNIXTIME(created_date /1000,'%Y-%m-%d %H:%i:%s'), '%Y%m%d')) =1");
        sql.append(" and usertype ='1';");
        Map<String,Object> res = jdbcTemplate.queryForMap(sql.toString());
        int sum =Integer.parseInt(String.valueOf(res.get("sum")));

        StatXx statXx = new StatXx();
        statXx.setName("日活跃量");
        statXx.setTotal(sum);
        statXx.setCycle("day");

        //由于统计获得的为昨天天的数据，所以这里获得昨天的日期
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE, -1);
        statXx.setStatDate(calendar.getTime());

        statXx.setType("2");
        statXx.setValid("1");
        statXx.setCreatedDate(System.currentTimeMillis());
        statRepository.save(statXx);

        this.updateExcuteTime(TaskEnum.用户活跃度统计.getId());

    };

    /**
     * 日增长用户量，统计前一天注册的用户总数
     */
    @Override
    public void StatRegisterUserNum(){

        StringBuffer sql = new StringBuffer();
        sql.append(" select count(1) as sum from u_user_xx uux where is_valid ='1' ");
        sql.append(" and to_days( now() )-to_days(date_format( FROM_UNIXTIME(created_date /1000,'%Y-%m-%d %H:%i:%s'), '%Y%m%d')) =1");
        sql.append(" and usertype ='1'");

        Map<String,Object> res = jdbcTemplate.queryForMap(sql.toString());
        int sum =Integer.parseInt(String.valueOf(res.get("sum")));

        StatXx statXx = new StatXx();
        statXx.setName("用户日增长量");
        statXx.setTotal(sum);
        statXx.setCycle("day");

        //由于统计获得的为昨天天的数据，所以这里获得昨天的日期
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE, -1);
        statXx.setStatDate(calendar.getTime());

        statXx.setType("3");
        statXx.setValid("1");
        statXx.setCreatedDate(System.currentTimeMillis());
        statRepository.save(statXx);

        this.updateExcuteTime(TaskEnum.日增长用户量.getId());
    };

    /**
     * 日信息量,统计的为前一天的总量
     */
    @Override
    public void statMsgNum(){

        StringBuffer sql = new StringBuffer();
        sql.append(" select count(1) as sum from u_msg_xx umx where is_valid ='1'");
        sql.append(" and to_days( now() )-to_days(date_format( FROM_UNIXTIME(created_date /1000,'%Y-%m-%d %H:%i:%s'), '%Y%m%d')) =1");
        Map<String,Object> res = jdbcTemplate.queryForMap(sql.toString());
        int sum =Integer.parseInt(String.valueOf(res.get("sum")));
        StatXx statXx = new StatXx();
        statXx.setName("日信息量");
        statXx.setTotal(sum);
        statXx.setCycle("day");

        //由于统计获得的为昨天天的数据，所以这里获得昨天的日期
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE, -1);
        statXx.setStatDate(calendar.getTime());

        statXx.setType("4");
        statXx.setValid("1");
        statXx.setCreatedDate(System.currentTimeMillis());
        statRepository.save(statXx);
        this.updateExcuteTime(TaskEnum.日信息量.getId());
    };
}
