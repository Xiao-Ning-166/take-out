package com.example.takeout.utils;

import com.example.takeout.constant.CommonConstant;
import lombok.Data;

import java.io.Serializable;

/**
 * 公共结果类
 * @author xiaoning
 * @date 2022/06/29
 */
@Data
public class Result<T> implements Serializable {

    private static final long serialVersionUID = -1868179996273105384L;

    /**
     * 是否成功
     */
    private Boolean success;

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 返回信息
     */
    private String message;

    /**
     * 返回数据
     */
    private T data;

    public Result() {

    }

    public Result<T> success(String message) {
        this.message = message;
        this.code = CommonConstant.SC_OK_200;
        this.success = true;
        return this;
    }

    public static<T> Result<T> OK() {
        Result<T> r = new Result<T>();
        r.setSuccess(true);
        r.setCode(CommonConstant.SC_OK_200);
        r.setMessage("成功");
        return r;
    }

    public static<T> Result<T> OK(T data) {
        Result<T> r = new Result<T>();
        r.setSuccess(true);
        r.setCode(CommonConstant.SC_OK_200);
        r.setData(data);
        return r;
    }

    public static<T> Result<T> OK(String msg, T data) {
        Result<T> r = new Result<T>();
        r.setSuccess(true);
        r.setCode(CommonConstant.SC_OK_200);
        r.setMessage(msg);
        r.setData(data);
        return r;
    }

    public static<T> Result<T> error(String msg, T data) {
        Result<T> r = new Result<T>();
        r.setSuccess(false);
        r.setCode(CommonConstant.SC_SERVER_INNER_ERROR_500);
        r.setMessage(msg);
        r.setData(data);
        return r;
    }

    public static Result<Object> error(String msg) {
        return error(CommonConstant.SC_SERVER_INNER_ERROR_500, msg);
    }

    /**
     * 错误结果
     * @param code 错误状态码
     * @param msg 错误信息
     * @return
     */
    public static Result<Object> error(int code, String msg) {
        Result<Object> r = new Result<Object>();
        r.setCode(code);
        r.setMessage(msg);
        r.setSuccess(false);
        return r;
    }

    /**
     * 服务器内部错误
     * @param message 错误信息
     * @return
     */
    public Result<T> error500(String message) {
        this.message = message;
        this.code = CommonConstant.SC_SERVER_INNER_ERROR_500;
        this.success = false;
        return this;
    }

    /**
     * 无权限访问返回结果
     */
    public static Result<Object> noauth(String msg) {
        return error(CommonConstant.SC_NO_AUTHORITY, msg);
    }
}
