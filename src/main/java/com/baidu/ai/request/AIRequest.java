package com.baidu.ai.request;

public class AIRequest {
    private String groupId;

    private String userId;

    private String image;

    private String userInfo;

    /**
     * 图片类型
     * BASE64:图片的base64值，base64编码后的图片数据，编码后的图片大小不超过2M；
     * URL:图片的 URL地址( 可能由于网络等原因导致下载图片时间过长)；
     * FACE_TOKEN：人脸图片的唯一标识，调用人脸检测接口时，会为每个人脸图片赋予一个唯一的FACE_TOKEN，同一张图片多次检测得到的FACE_TOKEN是同一个。
     */
    private String imageType;

    private String faceToken;

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(String userInfo) {
        this.userInfo = userInfo;
    }

    public String getImageType() {
        return imageType;
    }

    public void setImageType(String imageType) {
        this.imageType = imageType;
    }


    public String getFaceToken() {
        return faceToken;
    }

    public void setFaceToken(String faceToken) {
        this.faceToken = faceToken;
    }

    public AIRequest(String groupId, String userId, String image, String imageType) {
        this.groupId = groupId;
        this.userId = userId;
        this.image = image;
        this.imageType = imageType;
    }

    @Override
    public String toString() {
        return "AIRequest{" +
                "groupId='" + groupId + '\'' +
                ", userId='" + userId + '\'' +
                ", image='" + image + '\'' +
                ", userInfo='" + userInfo + '\'' +
                ", imageType='" + imageType + '\'' +
                '}';
    }
}
