/*
 * Copyright(c) 2019 cncounter.com All rights reserved.
 * distributed with this file and available online at
 * http://www.sinobest.cn
 */
package edu.lingnan.talklater.api.menu.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;



/**
 * MenuXx领域模型类
 * @author likunzhu
 */
@Entity
@Table(name = "u_menu_xx")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MenuXx  implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid")
    @Column(name = "id")
    private String id;

    /**
     * 父级菜单id
     */
    @Column(name = "f_id")
    private String fid;

    /**
     * 菜单名称
     */
    @Column(name = "name")
    private String name;

    /**
     * 菜单地址
     */
    @Column(name = "url")
    private String url;

    /**
     * 图标
     */
    @Column(name = "icon")
    private String icon;

    /**
     * 排序号
     */
    @Column(name = "order")
    private Integer order;

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
     * 创建时间
     */
    @Column(name = "created_date")
    private Long createdDate;


    /**
     * 修改时间
     */
    @Column(name = "modified_date")
    private Long modifiedDate;

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
     * @param fid the fid to set
     */
    public void setFid(String fid) {
        this.fid = fid;
    }

    /**
     * @return the fid
     */
    public String getFid() {
        return this.fid;
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
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return the url
     */
    public String getUrl() {
        return this.url;
    }

    /**
     * @param order the order to set
     */
    public void setOrder(Integer order) {
        this.order = order;
    }

    /**
     * @return the order
     */
    public Integer getOrder() {
        return this.order;
    }

    /**
     * @param bz the bz to set
     */
    public void setBz(String bz) {
        this.bz = bz;
    }

    /**
     * @return the bz
     */
    public String getBz() {
        return this.bz;
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

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Long getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Long createdDate) {
        this.createdDate = createdDate;
    }

    public Long getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Long modifiedDate) {
        this.modifiedDate = modifiedDate;
    }
}

