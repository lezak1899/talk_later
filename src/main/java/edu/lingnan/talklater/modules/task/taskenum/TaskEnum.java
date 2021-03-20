package edu.lingnan.talklater.modules.task.taskenum;

/**
 * Description:
 * date: 2021/3/16 14:47
 *
 * @author likunzhu
 * @since
 */
public enum TaskEnum {


    用户总量统计("189c4f4a-8620-11eb-a237-00ffbd07b1ad"),
    用户活跃度统计("37b97330-8632-11eb-a237-00ffbd07b1ad"),
    日增长用户量("3935b5e2-8632-11eb-a237-00ffbd07b1ad"),
    日信息量("3af80bdb-8632-11eb-a237-00ffbd07b1ad");




    public final String id;

    TaskEnum(String id){
        this.id = id;
    }

    public String getId() {
        return this.id;
    }
}
