package edu.lingnan.talklater.modules.friendsref.domain.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Description:
 * date: 2021/2/6 17:39
 *
 * @author likunzhu
 * @since
 */
@Entity
@Table(name = "v_friend_ref")
public class FriendsRefVo implements Serializable {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "friend_username")
    private String friendUsername;

    @Column(name = "is_valid")
    private String is_valid;

    @Column(name = "friend_face_img")
    private String friend_face_img;

    @Column(name = "friend_nickname")
    private String friend_nickname;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFriendUsername() {
        return friendUsername;
    }

    public void setFriendUsername(String friendUsername) {
        this.friendUsername = friendUsername;
    }

    public String getIs_valid() {
        return is_valid;
    }

    public void setIs_valid(String is_valid) {
        this.is_valid = is_valid;
    }

    public String getFriend_face_img() {
        return friend_face_img;
    }

    public void setFriend_face_img(String friend_face_img) {
        this.friend_face_img = friend_face_img;
    }

    public String getFriend_nickname() {
        return friend_nickname;
    }

    public void setFriend_nickname(String friend_nickname) {
        this.friend_nickname = friend_nickname;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (!(o instanceof FriendsRefVo)) return false;

        FriendsRefVo that = (FriendsRefVo) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (friendUsername != null ? !friendUsername.equals(that.friendUsername) : that.friendUsername != null)
            return false;
        if (is_valid != null ? !is_valid.equals(that.is_valid) : that.is_valid != null) return false;
        if (friend_face_img != null ? !friend_face_img.equals(that.friend_face_img) : that.friend_face_img != null)
            return false;
        return friend_nickname != null ? friend_nickname.equals(that.friend_nickname) : that.friend_nickname == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (friendUsername != null ? friendUsername.hashCode() : 0);
        result = 31 * result + (is_valid != null ? is_valid.hashCode() : 0);
        result = 31 * result + (friend_face_img != null ? friend_face_img.hashCode() : 0);
        result = 31 * result + (friend_nickname != null ? friend_nickname.hashCode() : 0);
        return result;
    }
}
