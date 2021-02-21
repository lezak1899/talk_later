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
    private String isValid;

    @Column(name = "friend_face_img")
    private String friendFaceImg;

    @Column(name = "friend_nickname")
    private String friendNickname;

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

    public String getIsValid() {
        return isValid;
    }

    public void setIsValid(String isValid) {
        this.isValid = isValid;
    }

    public String getFriendFaceImg() {
        return friendFaceImg;
    }

    public void setFriendFaceImg(String friendFaceImg) {
        this.friendFaceImg = friendFaceImg;
    }

    public String getFriendNickname() {
        return friendNickname;
    }

    public void setFriendNickname(String friendNickname) {
        this.friendNickname = friendNickname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FriendsRefVo)) return false;

        FriendsRefVo that = (FriendsRefVo) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (friendUsername != null ? !friendUsername.equals(that.friendUsername) : that.friendUsername != null)
            return false;
        if (isValid != null ? !isValid.equals(that.isValid) : that.isValid != null) return false;
        if (friendFaceImg != null ? !friendFaceImg.equals(that.friendFaceImg) : that.friendFaceImg != null)
            return false;
        return friendNickname != null ? friendNickname.equals(that.friendNickname) : that.friendNickname == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (friendUsername != null ? friendUsername.hashCode() : 0);
        result = 31 * result + (isValid != null ? isValid.hashCode() : 0);
        result = 31 * result + (friendFaceImg != null ? friendFaceImg.hashCode() : 0);
        result = 31 * result + (friendNickname != null ? friendNickname.hashCode() : 0);
        return result;
    }
}
