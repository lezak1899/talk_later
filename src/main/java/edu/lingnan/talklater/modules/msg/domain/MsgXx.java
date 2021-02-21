package edu.lingnan.talklater.modules.msg.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Description:
 * date: 2021/2/20 14:07
 *
 * @author likunzhu
 * @since
 */
@Entity
@Table(name = "u_msg_xx")
public class MsgXx implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid")
    @Column(name = "id")
    private String id;

    /**
     * 发送方id
     */
    @Column(name = "sender_id")
    private String senderId;

    /**
     * 接受方id
     */
    @Column(name = "recipient_id")
    private String recipientId;

    /**
     * 发送方用户名
     */
    @Column(name = "sender_username")
    private String senderUsername;

    /**
     * 接受方用户名
     */
    @Column(name = "recipient_username")
    private String recipientUsername;

    /**
     * 消息正文
     */
    @Column(name = "msg")
    private String msg;

    /**
     * 撤回标志，1：已撤回；2：未撤回；
     */
    @Column(name = "withdraw_flag")
    private String withdrawFlag;

    /**
     * 已读标志，1：已读；2：未读；
     */
    @Column(name = "readed_flag")
    private String readedFlag;


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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getRecipientId() {
        return recipientId;
    }

    public void setRecipientId(String recipientId) {
        this.recipientId = recipientId;
    }

    public String getSenderUsername() {
        return senderUsername;
    }

    public void setSenderUsername(String senderUsername) {
        this.senderUsername = senderUsername;
    }

    public String getRecipientUsername() {
        return recipientUsername;
    }

    public void setRecipientUsername(String recipientUsername) {
        this.recipientUsername = recipientUsername;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getWithdrawFlag() {
        return withdrawFlag;
    }

    public void setWithdrawFlag(String withdrawFlag) {
        this.withdrawFlag = withdrawFlag;
    }

    public String getReadedFlag() {
        return readedFlag;
    }

    public void setReadedFlag(String readedFlag) {
        this.readedFlag = readedFlag;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MsgXx)) return false;

        MsgXx msgXx = (MsgXx) o;

        if (id != null ? !id.equals(msgXx.id) : msgXx.id != null) return false;
        if (senderId != null ? !senderId.equals(msgXx.senderId) : msgXx.senderId != null) return false;
        if (recipientId != null ? !recipientId.equals(msgXx.recipientId) : msgXx.recipientId != null) return false;
        if (senderUsername != null ? !senderUsername.equals(msgXx.senderUsername) : msgXx.senderUsername != null)
            return false;
        if (recipientUsername != null ? !recipientUsername.equals(msgXx.recipientUsername) : msgXx.recipientUsername != null)
            return false;
        if (msg != null ? !msg.equals(msgXx.msg) : msgXx.msg != null) return false;
        if (withdrawFlag != null ? !withdrawFlag.equals(msgXx.withdrawFlag) : msgXx.withdrawFlag != null) return false;
        if (readedFlag != null ? !readedFlag.equals(msgXx.readedFlag) : msgXx.readedFlag != null) return false;
        if (valid != null ? !valid.equals(msgXx.valid) : msgXx.valid != null) return false;
        return createdDate != null ? createdDate.equals(msgXx.createdDate) : msgXx.createdDate == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (senderId != null ? senderId.hashCode() : 0);
        result = 31 * result + (recipientId != null ? recipientId.hashCode() : 0);
        result = 31 * result + (senderUsername != null ? senderUsername.hashCode() : 0);
        result = 31 * result + (recipientUsername != null ? recipientUsername.hashCode() : 0);
        result = 31 * result + (msg != null ? msg.hashCode() : 0);
        result = 31 * result + (withdrawFlag != null ? withdrawFlag.hashCode() : 0);
        result = 31 * result + (readedFlag != null ? readedFlag.hashCode() : 0);
        result = 31 * result + (valid != null ? valid.hashCode() : 0);
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        return result;
    }
}
