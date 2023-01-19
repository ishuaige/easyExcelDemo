package com.niuma.easyexceldemo.common;

import lombok.Data;

import java.io.Serializable;

/**
 * @author niumazlb
 * @create 2022-11-19 21:47
 */
@Data
public class BaseResponse<T> implements Serializable {

    private static final long serialVersionUID = -2236805258354282234L;

    private int code;
    private T data;
    private String message;

    public BaseResponse(int code, T data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
    }

    public BaseResponse(int code, T data) {
        this(code, data, "");
    }

    public BaseResponse(ErrorCode errorCode) {
        this(errorCode.getCode(), null, errorCode.getMessage());
    }


}
