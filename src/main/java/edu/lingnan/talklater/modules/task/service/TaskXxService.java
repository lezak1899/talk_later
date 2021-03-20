package edu.lingnan.talklater.modules.task.service;

import edu.lingnan.talklater.modules.stat.domain.StatXx;
import edu.lingnan.talklater.modules.stat.domain.request.StatQueryEntity;
import edu.lingnan.talklater.modules.task.domain.TaskXx;
import edu.lingnan.talklater.modules.task.repository.request.TaskXxQueryEntity;
import edu.lingnan.talklater.request.QueryEntity;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Description:
 * date: 2021/3/14 11:33
 *
 * @author likunzhu
 * @since
 */
public interface TaskXxService {

    /**
     * 查询所有可用的任务
     */
    public List<TaskXx> queryAllTaskXx();

    /**
     *  校验当前任务是否可用
     */
    public boolean verifyTask(String id);

    /**
     * 分页查询
     */
    public Page<TaskXx> queryTaskPage(TaskXxQueryEntity queryEntity);


    /**
     * 修改单个字段
     */
    public TaskXx modifyByZdmc(String taskId,String zdmc,String value);


    /**
     * 修改taskXx的name、cron属性
     * @param taskXx
     * @return
     */
    public int modify(TaskXx taskXx);


}
