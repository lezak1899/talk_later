
package edu.lingnan.talklater.modules.task.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

/**
 * StaskXx领域模型类
 * @author likunzhu
 */
@Entity
@Table(name = "s_task_xx")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class TaskXx implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid")
    @Column(name = "id")
    private String id;

    /**
     * 定时任务类型
     */
    @Column(name = "type")
    private String type;

    /**
     * 定时任务名称
     */
    @Column(name = "name")
    private String name;

    /**
     * 状态
     */
    @Column(name = "status")
    private String status;

    /**
     * cron
     */
    @Column(name = "cron")
    private String cron;

    /**
     * 上一次执行时间
     */
    @Column(name = "last_excute_time")
    private Date lastExcuteTime;

    /**
     * beanName
     */
    @Column(name = "beanname")
    private String beanName;

    /**
     * methodName
     */
    @Column(name = "methodname")
    private String methodName;

    /**
     * bz
     */
    @Column(name = "bz")
    private String bz;

    /**
     * 是否有效，字典(0否；1是)，缺省值为1
     */
    @Column(name = "is_valid")
    private String valid;

    /**
     * 新增时间
     */
    @Column(name = "created_date")
    private Long createdDate;

    /**
     * 修改时间
     */
    @Column(name = "modified_date")
    private Long modified_date;

    /**
     * 删除时间
     */
    @Column(name = "deleted_date")
    private Long deletedDate;

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the id
     */
    public String getId() {
        return this.id;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the type
     */
    public String getType() {
        return this.type;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the name
     */
    public String getName() {
        return this.name;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return this.status;
    }

    /**
     * @param cron the cron to set
     */
    public void setCron(String cron) {
        this.cron = cron;
    }

    /**
     * @return the cron
     */
    public String getCron() {
        return this.cron;
    }

    public Date getLastExcuteTime() {
        return lastExcuteTime;
    }

    public void setLastExcuteTime(Date lastExcuteTime) {
        this.lastExcuteTime = lastExcuteTime;
    }

    /**
     * @param beanName the beanName to set
     */
    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    /**
     * @return the beanName
     */
    public String getBeanName() {
        return this.beanName;
    }

    /**
     * @param methodName the methodName to set
     */
    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    /**
     * @return the methodName
     */
    public String getMethodName() {
        return this.methodName;
    }

    public String getValid() {
        return valid;
    }

    public void setValid(String valid) {
        this.valid = valid;
    }

    /**
     * @param deletedDate the deletedDate to set
     */
    public void setDeletedDate(Long deletedDate) {
        this.deletedDate = deletedDate;
    }

    /**
     * @return the deletedDate
     */
    public Long getDeletedDate() {
        return this.deletedDate;
    }

    public Long getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Long createdDate) {
        this.createdDate = createdDate;
    }

    public Long getModified_date() {
        return modified_date;
    }

    public void setModified_date(Long modified_date) {
        this.modified_date = modified_date;
    }
}

