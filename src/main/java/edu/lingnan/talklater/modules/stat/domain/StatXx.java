package edu.lingnan.talklater.modules.stat.domain;

import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * Description:
 * date: 2021/3/16 13:54
 *
 * @author likunzhu
 * @since
 */
@Entity
@Table(name = "s_stat_xx")
public class StatXx  implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid")
    @Column(name = "id")
    private String id;

    /**
     * 统计类型
     */
    @Column(name = "type")
    private String type;

    /**
     * 统计名称
     */
    @Column(name = "name")
    private String name;

    /**
     * 数据总量
     */
    @Column(name = "total")
    private Integer total;

    /**
     * 目标总量
     */
    @Column(name = "target_num")
    private Integer targetNum;

    /**
     * 剩余总量
     */
    @Column(name = "other_num")
    private Integer otherNum;

    /**
     * 目标比例
     */
    @Column(name = "target_rate")
    private java.math.BigDecimal targetRate;

    /**
     * 统计时间
     */
    @Column(name = "stat_date")
    private Date statDate;

    /**
     * 周期
     */
    @Column(name = "cycle")
    private String cycle;

    /**
     * 备注
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getTargetNum() {
        return targetNum;
    }

    public void setTargetNum(Integer targetNum) {
        this.targetNum = targetNum;
    }

    public Integer getOtherNum() {
        return otherNum;
    }

    public void setOtherNum(Integer otherNum) {
        this.otherNum = otherNum;
    }

    public BigDecimal getTargetRate() {
        return targetRate;
    }

    public void setTargetRate(BigDecimal targetRate) {
        this.targetRate = targetRate;
    }

    public Date getStatDate() {
        return statDate;
    }

    public void setStatDate(Date statDate) {
        this.statDate = statDate;
    }

    public String getCycle() {
        return cycle;
    }

    public void setCycle(String cycle) {
        this.cycle = cycle;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    public String getValid() {
        return valid;
    }

    public void setValid(String valid) {
        this.valid = valid;
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

    public Long getDeletedDate() {
        return deletedDate;
    }

    public void setDeletedDate(Long deletedDate) {
        this.deletedDate = deletedDate;
    }
}
