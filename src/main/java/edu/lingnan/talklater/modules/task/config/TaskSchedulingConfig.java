package edu.lingnan.talklater.modules.task.config;

import edu.lingnan.talklater.modules.job.Service.JobService;
import edu.lingnan.talklater.modules.task.domain.TaskXx;
import edu.lingnan.talklater.modules.task.repository.TaskXxRepository;
import edu.lingnan.talklater.modules.task.service.TaskXxService;
import edu.lingnan.talklater.modules.task.taskenum.TaskEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.time.LocalDateTime;
import java.util.List;


//@Slf4j
@Configuration
@EnableScheduling
//@ConditionalOnProperty(prefix = "scheduling", name = "enabled", havingValue = "true")
public class TaskSchedulingConfig implements SchedulingConfigurer {

    @Autowired
    private TaskXxService taskXxService;

    @Autowired
    private JobService jobService;

    //任务调度注册器
    private ScheduledTaskRegistrar registrars;


    /**
     * 统计全部用户
     */
    public void StatUserNum(){
        if(taskXxService.verifyTask(TaskEnum.用户总量统计.getId())){
            System.out.println("用户总量统计被执行。。。。。。。");
            jobService.StatUserNum();
        }
    }

    /**
     * 用户活跃度统计，统计前一天登录的用户
     */
    public void StatActiveUserRate(){
        if(taskXxService.verifyTask(TaskEnum.用户活跃度统计.getId())){
            System.out.println("用户活跃度统计被执行。。。。。。。");
            jobService.StatActiveUserRate();
        }

    };

    /**
     * 日增长用户，统计前一天注册的用户总数
     */
    public void StatRegisterUserNum(){
        if(taskXxService.verifyTask(TaskEnum.日增长用户量.getId())){
            System.out.println("日增长用户量统计被执行。。。。。。。");
            jobService.StatRegisterUserNum();
        }

    };

    /**
     * 日信息量统计
     */
    public void statMsgNum(){
        if(taskXxService.verifyTask(TaskEnum.日信息量.getId())){
            System.out.println("日增长用户量统计被执行。。。。。。。");
            jobService.statMsgNum();
        }
    };


    //重启项目时会执行
    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {

//        获得所有任务
        List<TaskXx> taskXxList = taskXxService.queryAllTaskXx();

        //将所有任务加入到框架的调度中去
        for(TaskXx item:taskXxList){
            String springBean = item.getBeanName();
            String method = item.getMethodName();
            String taskId = item.getId();
            Runnable task = new TaskModel(springBean,method);
            Trigger trigger = new TriggerModel(taskId);
            taskRegistrar.addTriggerTask(task, trigger);
        }

        this.registrars = taskRegistrar;
    }


}




