package edu.lingnan.talklater.modules.requestxx.domain.vo;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Description:
 * date: 2021/2/5 16:14
 *
 * @author likunzhu
 * @since
 */

@Entity
@Table(name = "v_request_xx")
public class RequestXxVo {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "sender_username")
    private String senderUsername;

    @Column(name = "recipient_username")
    private String recipientUsername;

    @Column(name = "sender_face_img")
    private String senderFaceImg;

    @Column(name = "sender_nickname")
    private String senderNickname;

    @Column(name = "request_state")
    private String request_state;

    @Column(name = "is_valid")
    private String is_valid;

    @Column(name = "created_date")
    private String createdDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getSenderFaceImg() {
        return senderFaceImg;
    }

    public void setSenderFaceImg(String senderFaceImg) {
        this.senderFaceImg = senderFaceImg;
    }

    public String getSenderNickname() {
        return senderNickname;
    }

    public void setSenderNickname(String senderNickname) {
        this.senderNickname = senderNickname;
    }

    public String getRequest_state() {
        return request_state;
    }

    public void setRequest_state(String request_state) {
        this.request_state = request_state;
    }

    public String getIs_valid() {
        return is_valid;
    }

    public void setIs_valid(String is_valid) {
        this.is_valid = is_valid;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RequestXxVo)) return false;

        RequestXxVo that = (RequestXxVo) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (senderUsername != null ? !senderUsername.equals(that.senderUsername) : that.senderUsername != null)
            return false;
        if (recipientUsername != null ? !recipientUsername.equals(that.recipientUsername) : that.recipientUsername != null)
            return false;
        if (senderFaceImg != null ? !senderFaceImg.equals(that.senderFaceImg) : that.senderFaceImg != null)
            return false;
        if (senderNickname != null ? !senderNickname.equals(that.senderNickname) : that.senderNickname != null)
            return false;
        if (request_state != null ? !request_state.equals(that.request_state) : that.request_state != null)
            return false;
        if (is_valid != null ? !is_valid.equals(that.is_valid) : that.is_valid != null) return false;
        return createdDate != null ? createdDate.equals(that.createdDate) : that.createdDate == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (senderUsername != null ? senderUsername.hashCode() : 0);
        result = 31 * result + (recipientUsername != null ? recipientUsername.hashCode() : 0);
        result = 31 * result + (senderFaceImg != null ? senderFaceImg.hashCode() : 0);
        result = 31 * result + (senderNickname != null ? senderNickname.hashCode() : 0);
        result = 31 * result + (request_state != null ? request_state.hashCode() : 0);
        result = 31 * result + (is_valid != null ? is_valid.hashCode() : 0);
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        return result;
    }
}
