package com.example.entity.constants;

/**
 * @Author: mxx
 * @Title: Constants
 * @Description:
 * @Date: 2022/4/29
 */
public enum Constants {
    /**
     * 常量对象
     */
    SUCCESS(200, "请求成功"),
    PARAM_ERROR(400, "请求失败"),
    AUTH_ERROR(401, "系统错误"),
    PERMISSION_ERROR(403, "没有权限"),
    METHOD_NOT_SUPPORT(405, "请求方法错误"),
    SYSTEM_ERROR(500, "请求失败");

    private Integer code;
    private String msg;

    Constants(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
