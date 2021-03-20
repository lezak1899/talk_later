package edu.lingnan.talklater.modules.task.config;


import edu.lingnan.talklater.modules.task.domain.TaskXx;
import edu.lingnan.talklater.modules.task.repository.TaskXxRepository;
import edu.lingnan.talklater.utils.SpringBeanUtil;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.support.CronTrigger;

import java.sql.Types;
import java.util.Date;

/**
 * 每次执行完定时任务，会被触发，这里用于获取下一次执行的时间
 * 用于实现动态修改定时任务
 */
public class TriggerModel implements Trigger {

    private String jobId;

    public TriggerModel(String jobId){
        this.jobId = jobId;
    }

    @Override
    public Date nextExecutionTime(TriggerContext triggerContext) {
//        //更新task表中执行的时间
//        this.updateExcuteTime(triggerContext.lastCompletionTime());

        //每一次任务触发，都会执行这里的方法一次，重新获取下一次的执行时间
        String cron = this.getCronByJobId();
        CronTrigger trigger = new CronTrigger(cron);
        //根据当前任务执行的时间和cron推断下一次执行的时间
        Date nextExec = trigger.nextExecutionTime(triggerContext);
        return nextExec;
    }

    //获得下次执行的时间
    private String getCronByJobId(){
        TaskXxRepository taskXxRepository = (TaskXxRepository) SpringBeanUtil.getBean("taskXxRepository");
        TaskXx taskXx = taskXxRepository.findById(this.jobId).get();
        return taskXx.getCron();

    }

//    private void updateExcuteTime(Date lastCompletionTime){
//        StringBuffer sql = new StringBuffer();
//        sql.append(" update s_task_xx set last_excute_time = ? where id = ?");
//        JdbcTemplate jdbcTemplate = (JdbcTemplate) SpringBeanUtil.getBean("jdbcTemplate");
//        jdbcTemplate.update(sql.toString(),new Object[]{lastCompletionTime,this.jobId},new int[]{Types.DATE,Types.VARCHAR});
//
//    }

}
