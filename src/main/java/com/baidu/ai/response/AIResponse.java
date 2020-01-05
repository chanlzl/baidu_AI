package com.baidu.ai.response;

import com.baidu.ai.enums.StatusEnum;

import java.util.List;

public class AIResponse<T> {

    private Integer code;

    private String message;

    private T data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public AIResponse(StatusEnum statusEnum) {
        this.code = statusEnum.getCode();
        this.message = statusEnum.getMessage();
    }

    public AIResponse(StatusEnum statusEnum, T data) {
        this.code = statusEnum.getCode();
        this.message = statusEnum.getMessage();
        this.data = data;
    }

    public AIResponse(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
