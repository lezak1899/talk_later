/*
 * Copyright(c) 2019 cncounter.com All rights reserved.
 * distributed with this file and available online at
 * http://www.sinobest.cn
 */
package edu.lingnan.talklater.api.role.domiain;

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
 * RoleXx领域模型类
 * @author likunzhu
 */
@Entity
@Table(name = "u_role_xx")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class RoleXx implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid")
    @Column(name = "id")
    private String id;

    /**
     * 角色名称
     */
    @Column(name = "name")
    private String name;

    /**
     * 角色类型
     */
    @Column(name = "type")
    private String type;

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

