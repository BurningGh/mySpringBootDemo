package com.example.validator.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

@Data
@Builder
public class Result<T> implements Serializable {
    private int code;
    private boolean result;
    private String msg;
    private Object data;

    public Result setCode(int code) {
        this.code = code;
        return this;
    }

    public Result setResult(boolean result) {
        this.result = result;
        return this;
    }

    public Result setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public Result setData(Object data) {
        this.data = data;
        return this;
    }

    /**
     * 返回消息
     *
     * @param msg  消息
     * @param code 状态码
     * @param re   成功标识
     * @param data 数据
     * @return 消息
     */
    public static Result msg(String msg, int code, boolean re, Object data) {
        return Result.builder().build().setMsg(msg).setCode(code).setResult(re).setData(data);
    }

    /**
     * 返回成功消息
     *
     * @return 成功消息
     */
    public static Result success() {
        String message = "操作成功";
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        HttpServletRequest request = attributes.getRequest();
        String method = request.getMethod();
        switch (method.toUpperCase()) {
            case "GET":
                message = "获取数据成功";
                break;
            case "POST":
                message = "提交数据成功";
                break;
            case "PUT":
                message = "更新数据成功";
                break;
            case "DELETE":
                message = "删除数据成功";
                break;
        }
        return Result.success(message);
    }

    /**
     * 返回成功消息
     *
     * @param msg 消息
     * @return 成功消息
     */
    public static Result success(String msg) {
        return Result.success(msg, 200);
    }

    /**
     * 返回成功消息
     *
     * @param msg  消息
     * @param code 状态码
     * @return 成功消息
     */
    public static Result success(String msg, int code) {
        return Result.success(msg, code, true);
    }

    /**
     * 返回成功消息
     *
     * @param msg  消息
     * @param code 状态码
     * @param re   标识
     * @return 成功消息
     */
    public static Result success(String msg, int code, boolean re) {
        return Result.msg(msg, code, re, null);
    }

    /**
     * 返回成功消息
     *
     * @param data 数据
     * @return 成功消息
     */
    public static Result success(Object data) {
        return Result.msg("操作成功", 200, true, data);
    }

    /**
     * 返回失败消息
     *
     * @return 失败消息
     */
    public static Result error() {
        String message = "操作失败";
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        HttpServletRequest request = attributes.getRequest();
        String method = request.getMethod();
        switch (method.toUpperCase()) {
            case "GET":
                message = "获取数据失败";
                break;
            case "POST":
                message = "提交数据失败";
                break;
            case "PUT":
                message = "更新数据失败";
                break;
            case "DELETE":
                message = "删除数据失败";
                break;
        }
        return Result.success(message);
    }

    /**
     * 返回失败消息
     *
     * @param msg 消息
     * @return 失败消息
     */
    public static Result error(String msg) {
        return Result.success(msg, 500);
    }

    /**
     * 返回失败消息
     *
     * @param msg  消息
     * @param code 状态码
     * @return 失败消息
     */
    public static Result error(String msg, int code) {
        return Result.success(msg, code, false);
    }

    /**
     * 返回失败消息
     *
     * @param msg  消息
     * @param code 状态码
     * @param re   标识
     * @return 失败消息
     */
    public static Result error(String msg, int code, boolean re) {
        return Result.msg(msg, code, re, null);
    }

    /**
     * 返回失败消息
     *
     * @param data 数据
     * @return 失败消息
     */
    public static Result error(Object data) {
        return Result.msg("操作失败", 500, false, data);
    }
}