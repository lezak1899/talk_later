/*
 * Copyright(c) 2019 cncounter.com All rights reserved.
 * distributed with this file and available online at
 * http://www.sinobest.cn
 */
package edu.lingnan.talklater.modules.user.domain;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;



/**
 * @author likunzhu
 */
@Entity
@Table(name = "u_user_xx")
public class UserXx  implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid")
    @Column(name = "id")
    private String id;

    /**
     * 用户类型，1： 普通用户；2：管理员
     */
    @Column(name = "usertype")
    private String usertype;

    /**
     * 用户名称，用于登陆
     */
    @Column(name = "username")
    private String username;

    /**
     * 性别
     */
    @Column(name = "sex")
    private String sex;

    /**
     * 密码
     */
    @Column(name = "password")
    private String password;

    /**
     * 手机号码
     */
    @Column(name = "phone")
    private String phone;

    /**
     * 头像预览图
     */
    @Column(name = "face_img")
    private String faceImg;

    /**
     * 头像像完整图
     */
    @Column(name = "face_img_whole")
    private String faceImgWhole;

    /**
     * 昵称
     */
    @Column(name = "nickname")
    private String nickname;

    /**
     * 二维码
     */
    @Column(name = "qrcode")
    private String qrcode;

    /**
     * 设备id
     */
    @Column(name = "plus_id")
    private String plusId;

    /**
     * 个性签名
     */
    @Column(name = "fun_signature")
    private String funSignature;

    /**
     * 上一次登录时间
     */
    @Column(name = "last_login_date")
    private Long lastLoginDate;

    /**
     * 上一次登录地点
     */
    @Column(name = "last_login_location")
    private String lastLoginLocation;

    /**
     * 上一次登录设备信息
     */
    @Column(name = "last_login_equipment")
    private String lastLoginEquipment;

    /**
     * 是否有效，字典(0否；1是)，缺省值为1
     */
    @Column(name = "is_valid")
    private Boolean valid;

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
     * @param nickname the nickname to set
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * @return the nickname
     */
    public String getNickname() {
        return this.nickname;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserXx userXx = (UserXx) o;
        return Objects.equals(id, userXx.id) &&
                Objects.equals(usertype, userXx.usertype) &&
                Objects.equals(username, userXx.username) &&
                Objects.equals(sex, userXx.sex) &&
                Objects.equals(password, userXx.password) &&
                Objects.equals(phone, userXx.phone) &&
                Objects.equals(faceImg, userXx.faceImg) &&
                Objects.equals(faceImgWhole, userXx.faceImgWhole) &&
                Objects.equals(nickname, userXx.nickname) &&
                Objects.equals(qrcode, userXx.qrcode) &&
                Objects.equals(plusId, userXx.plusId) &&
                Objects.equals(funSignature, userXx.funSignature) &&
                Objects.equals(lastLoginDate, userXx.lastLoginDate) &&
                Objects.equals(lastLoginLocation, userXx.lastLoginLocation) &&
                Objects.equals(lastLoginEquipment, userXx.lastLoginEquipment) &&
                Objects.equals(valid, userXx.valid) &&
                Objects.equals(deletedDate, userXx.deletedDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, usertype, username, sex, password, phone, faceImg, faceImgWhole, nickname, qrcode, plusId, funSignature, lastLoginDate, lastLoginLocation, lastLoginEquipment, valid, deletedDate);
    }
}

