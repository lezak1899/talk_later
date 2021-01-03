/*
 * Copyright(c) 2019 cncounter.com All rights reserved.
 * distributed with this file and available online at
 * http://www.sinobest.cn
 */
package edu.lingnan.talklater.modules.user.domain.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 查询UserXx请求dto类
 * @author likunzhu
 */
@ApiModel(value = "UserXxRequestDTO", description = "查询UserXx请求dto类")
public class UserXxRequestDTO implements Serializable {
    
    @ApiModelProperty(value = "主键")
    private String id;

    /**
     * 用户类型，1： 普通用户；2：管理员
     */
    @ApiModelProperty(value = "用户类型，1： 普通用户；2：管理员")
    private String usertype;

    /**
     * 用户名称，用于登陆
     */
    @ApiModelProperty(value = "用户名称，用于登陆")
    private String username;

    /**
     * 性别
     */
    @ApiModelProperty(value = "性别")
    private String sex;

    /**
     * 密码
     */
    @ApiModelProperty(value = "密码")
    private String password;

    /**
     * 手机号码
     */
    @ApiModelProperty(value = "手机号码")
    private String phone;

    /**
     * 头像预览图
     */
    @ApiModelProperty(value = "头像预览图")
    private String faceImg;

    /**
     * 头像像完整图
     */
    @ApiModelProperty(value = "头像像完整图")
    private String faceImgWhole;

    /**
     * 昵称
     */
    @ApiModelProperty(value = "昵称")
    private String nicename;

    /**
     * 二维码
     */
    @ApiModelProperty(value = "二维码")
    private String qrcode;

    /**
     * 设备id
     */
    @ApiModelProperty(value = "设备id")
    private String plusId;

    /**
     * 个性签名
     */
    @ApiModelProperty(value = "个性签名")
    private String funSignature;

    /**
     * 上一次登录时间
     */
    @ApiModelProperty(value = "上一次登录时间")
    private Long lastLoginDate;

    /**
     * 上一次登录地点
     */
    @ApiModelProperty(value = "上一次登录地点")
    private String lastLoginLocation;

    /**
     * 上一次登录设备信息
     */
    @ApiModelProperty(value = "上一次登录设备信息")
    private String lastLoginEquipment;

    /**
     * 是否有效，字典(0否；1是)，缺省值为1
     */
    @ApiModelProperty(value = "是否有效，字典(0否；1是)，缺省值为1")
    private Boolean valid;

    /**
     * 删除时间
     */
    @ApiModelProperty(value = "删除时间")
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
     * @param usertype the usertype to set
     */
    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

    /**
     * @return the usertype
     */
    public String getUsertype() {
        return this.usertype;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * @param sex the sex to set
     */
    public void setSex(String sex) {
        this.sex = sex;
    }

    /**
     * @return the sex
     */
    public String getSex() {
        return this.sex;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return this.phone;
    }

    /**
     * @param faceImg the faceImg to set
     */
    public void setFaceImg(String faceImg) {
        this.faceImg = faceImg;
    }

    /**
     * @return the faceImg
     */
    public String getFaceImg() {
        return this.faceImg;
    }

    /**
     * @param faceImgWhole the faceImgWhole to set
     */
    public void setFaceImgWhole(String faceImgWhole) {
        this.faceImgWhole = faceImgWhole;
    }

    /**
     * @return the faceImgWhole
     */
    public String getFaceImgWhole() {
        return this.faceImgWhole;
    }

    /**
     * @param nicename the nicename to set
     */
    public void setNicename(String nicename) {
        this.nicename = nicename;
    }

    /**
     * @return the nicename
     */
    public String getNicename() {
        return this.nicename;
    }

    /**
     * @param qrcode the qrcode to set
     */
    public void setQrcode(String qrcode) {
        this.qrcode = qrcode;
    }

    /**
     * @return the qrcode
     */
    public String getQrcode() {
        return this.qrcode;
    }

    /**
     * @param plusId the plusId to set
     */
    public void setPlusId(String plusId) {
        this.plusId = plusId;
    }

    /**
     * @return the plusId
     */
    public String getPlusId() {
        return this.plusId;
    }

    /**
     * @param funSignature the funSignature to set
     */
    public void setFunSignature(String funSignature) {
        this.funSignature = funSignature;
    }

    /**
     * @return the funSignature
     */
    public String getFunSignature() {
        return this.funSignature;
    }

    /**
     * @param lastLoginDate the lastLoginDate to set
     */
    public void setLastLoginDate(Long lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    /**
     * @return the lastLoginDate
     */
    public Long getLastLoginDate() {
        return this.lastLoginDate;
    }

    /**
     * @param lastLoginLocation the lastLoginLocation to set
     */
    public void setLastLoginLocation(String lastLoginLocation) {
        this.lastLoginLocation = lastLoginLocation;
    }

    /**
     * @return the lastLoginLocation
     */
    public String getLastLoginLocation() {
        return this.lastLoginLocation;
    }

    /**
     * @param lastLoginEquipment the lastLoginEquipment to set
     */
    public void setLastLoginEquipment(String lastLoginEquipment) {
        this.lastLoginEquipment = lastLoginEquipment;
    }

    /**
     * @return the lastLoginEquipment
     */
    public String getLastLoginEquipment() {
        return this.lastLoginEquipment;
    }

    /**
     * @param valid the valid to set
     */
    public void setValid(Boolean valid) {
        this.valid = valid;
    }

    /**
     * @return the valid
     */
    public Boolean getValid() {
        return this.valid;
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
}
