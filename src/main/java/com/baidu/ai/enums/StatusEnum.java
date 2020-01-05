package com.baidu.ai.enums;

public enum StatusEnum {

    SUCCESS(0,"成功"),
    FAILURE(1,"失败"),
    INPUT_PARAMETER_INVALID(10001,"输入参数无效"),
    GROUP_EXIST(10002,"用户组已存在"),
    GROUP_NOT_EXIST(10003,"用户组不存在"),
    FACE_NOT_EXIST(10004,"人脸不存在"),
    FACE_EXIST(10005,"人脸已存在"),
    UNKNOWN_ERROR(10006,"未知错误"),



    ;

    private int code;

    private String message;

    StatusEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
