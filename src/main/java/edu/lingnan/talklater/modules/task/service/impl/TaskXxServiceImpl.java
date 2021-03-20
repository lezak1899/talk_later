package edu.lingnan.talklater.modules.task.service.impl;

import edu.lingnan.talklater.modules.stat.domain.StatXx;
import edu.lingnan.talklater.modules.task.domain.TaskXx;
import edu.lingnan.talklater.modules.task.repository.TaskXxRepository;
import edu.lingnan.talklater.modules.task.repository.request.TaskXxQueryEntity;
import edu.lingnan.talklater.modules.task.service.TaskXxService;
import edu.lingnan.talklater.modules.user.domain.UserXx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Types;
import java.util.List;

/**
 * Description:
 * date: 2021/3/14 11:33
 *
 * @author likunzhu
 * @since
 */

@Service
@Transactional
public class TaskXxServiceImpl implements TaskXxService {

    @Autowired
    private TaskXxRepository taskXxRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public List<TaskXx> queryAllTaskXx() {

        TaskXx taskXx = new TaskXx();
        taskXx.setStatus("1");
        taskXx.setValid("1");

        Example example = Example.of(taskXx);

        List<TaskXx> res =taskXxRepository.findAll(example);
        return res;
    }

    @Override
    public boolean verifyTask(String id) {
        TaskXx taskXx = new TaskXx();
        taskXx.setId(id);
        taskXx.setStatus("1");
        taskXx.setValid("1");
        Example example = Example.of(taskXx);
        List<TaskXx> res =taskXxRepository.findAll(example);
        if(res.size()<1) return false;
        return true;
    }

    /**
     * 分页查询
     */
    @Override
     public Page<TaskXx> queryTaskPage(TaskXxQueryEntity queryEntity){
        //定义过滤模板的实体类
        TaskXx taskXx = new TaskXx();

        //过滤条件
        if(queryEntity.getType()!=""){
            taskXx.setType(queryEntity.getType());
        }
        if(queryEntity.getName()!=""){
            taskXx.setName(queryEntity.getName());
        }

        //生成example
        ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreNullValues();
        Example<TaskXx> example = Example.of(taskXx, matcher);

        //分页，并且通过created_date字段进行降序排序
        PageRequest of = PageRequest.of(queryEntity.getPageNum()-1, queryEntity.getPageSize(), Sort.Direction.DESC, "createdDate");

        Page<TaskXx> taskXxPage = taskXxRepository.findAll(example,of);

        return taskXxPage;
    }

    /**
     * 修改单个字段
     */
     public TaskXx modifyByZdmc(String taskId,String zdmc,String value){

         StringBuffer sql= new StringBuffer();
         sql.append(" update s_task_xx");
         switch(zdmc){
             case "is_valid" :
                 sql.append(" set is_valid = ? ");
                 break;
         }
         sql.append(" where id = ?");
         int n= jdbcTemplate.update(sql.toString(),new Object[]{value,taskId},new int[]{Types.VARCHAR,Types.VARCHAR});

         if(n<1) return null;

         TaskXx taskXx = taskXxRepository.findById(taskId).get();

         return taskXx;
     };


    /**
     * 修改taskXx的name、cron属性
     * @param taskXx
     * @return
     */
    public int modify(TaskXx taskXx){
        StringBuffer sql = new StringBuffer();
        sql.append(" update s_task_xx set name = ?,cron =? where id=?");
        return jdbcTemplate.update(sql.toString(),new Object[]{taskXx.getName(),taskXx.getCron(),taskXx.getId()},new int[]{Types.VARCHAR,Types.VARCHAR,Types.VARCHAR});
    };
}
