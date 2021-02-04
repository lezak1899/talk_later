package edu.lingnan.talklater.modules.requestxx.domain;



import javax.persistence.*;
import java.io.Serializable;



/**
 * @author likunzhu
 */
@Entity
@Table(name = "u_request_xx")
public class RequestXx  implements Serializable {

    @Id
    @Column(name = "id")
    private String id;

    /**
     * 发送方id
     */
    @Column(name = "sender_id")
    private String senderId;

    /**
     * 发送方用户名
     */
    @Column(name = "sender_username")
    private String senderUsername;

    /**
     * 请求方id
     */
    @Column(name = "recipient_id")
    private String recipientId;

    /**
     * 请求方用户名
     */
    @Column(name = "recipient_username")
    private String recipientUsername;

    /**
     * 请求状态
     */
    @Column(name = "request_state")
    private String request_state;


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

    public String getSenderId() {
        return senderId;
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

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getRecipientId() {
        return recipientId;
    }

    public void setRecipientId(String recipientId) {
        this.recipientId = recipientId;
    }

    public String getRequest_state() {
        return request_state;
    }

    public void setRequest_state(String request_state) {
        this.request_state = request_state;
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
        if (!(o instanceof RequestXx)) return false;

        RequestXx requestXx = (RequestXx) o;

        if (id != null ? !id.equals(requestXx.id) : requestXx.id != null) return false;
        if (senderId != null ? !senderId.equals(requestXx.senderId) : requestXx.senderId != null) return false;
        if (senderUsername != null ? !senderUsername.equals(requestXx.senderUsername) : requestXx.senderUsername != null)
            return false;
        if (recipientId != null ? !recipientId.equals(requestXx.recipientId) : requestXx.recipientId != null)
            return false;
        if (recipientUsername != null ? !recipientUsername.equals(requestXx.recipientUsername) : requestXx.recipientUsername != null)
            return false;
        if (request_state != null ? !request_state.equals(requestXx.request_state) : requestXx.request_state != null)
            return false;
        if (valid != null ? !valid.equals(requestXx.valid) : requestXx.valid != null) return false;
        if (createdDate != null ? !createdDate.equals(requestXx.createdDate) : requestXx.createdDate != null)
            return false;
        if (modified_date != null ? !modified_date.equals(requestXx.modified_date) : requestXx.modified_date != null)
            return false;
        return deletedDate != null ? deletedDate.equals(requestXx.deletedDate) : requestXx.deletedDate == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (senderId != null ? senderId.hashCode() : 0);
        result = 31 * result + (senderUsername != null ? senderUsername.hashCode() : 0);
        result = 31 * result + (recipientId != null ? recipientId.hashCode() : 0);
        result = 31 * result + (recipientUsername != null ? recipientUsername.hashCode() : 0);
        result = 31 * result + (request_state != null ? request_state.hashCode() : 0);
        result = 31 * result + (valid != null ? valid.hashCode() : 0);
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        result = 31 * result + (modified_date != null ? modified_date.hashCode() : 0);
        result = 31 * result + (deletedDate != null ? deletedDate.hashCode() : 0);
        return result;
    }
}

