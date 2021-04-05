package edu.lingnan.talklater.modules.user.domain.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * Description:
 * date: 2021/4/4 13:33
 *
 * @author likunzhu
 * @since
 */
@ApiModel(value = "UserXxRequestDTO", description = "头像上传Dto类")
public class UserFaceImgRequestDto implements Serializable {

    @ApiModelProperty(value = "用户Id")
    private String userId;

    @ApiModelProperty(value = "头像高清图")
    private String faceImgWhole;

    @ApiModelProperty(value = "头像缩略图,80*80")
    private String faceImg;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFaceImgWhole() {
        return faceImgWhole;
    }

    public void setFaceImgWhole(String faceImgWhole) {
        this.faceImgWhole = faceImgWhole;
    }

    public String getFaceImg() {
        return faceImg;
    }

    public void setFaceImg(String faceImg) {
        this.faceImg = faceImg;
    }
}
