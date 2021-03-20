package edu.lingnan.talklater.modules.job.Service;

/**
 * Description:
 * date: 2021/3/16 11:41
 *
 * @author likunzhu
 * @since
 */
public interface JobService {


    /**
     * 统计用户总量，周期day
     */
    public void StatUserNum();

    /**
     * 用户活跃度统计，统计前一天登录的用户
     */
    public void StatActiveUserRate();

    /**
     * 日增长用户，统计前一天注册的用户总数
     */
    public void StatRegisterUserNum();

    /**
     * 日信息量统计
     */
    public void statMsgNum();
}
