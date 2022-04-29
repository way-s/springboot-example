package com.example.entity.vo;

import com.example.entity.constants.Constants;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: mxx
 * @Title: Result
 * @Description:
 * @Date: 2022/4/29
 */
@Data
@ApiModel(
        description = "请求结果"
)
public class Result<T> implements Serializable {

    private static final long serialVersionUID = 6052044318782038985L;

    @ApiModelProperty(value = "状态码")
    private Integer code;

    @ApiModelProperty(value = "结果信息")
    private String msg;

    @ApiModelProperty(value = "返回的对象信息")
    private T data;

    @ApiModelProperty(value = "成功标识")
    private Boolean success = false;

    public static <T> Result<T> fail() {
        Result<T> tResult = new Result<>();
        tResult.setCode(Constants.SYSTEM_ERROR.getCode());
        tResult.setMsg(Constants.SYSTEM_ERROR.getMsg());
        return tResult;
    }

    public static <T> Result<T> fail(String msg) {
        Result<T> tResult = new Result<>();
        tResult.setCode(Constants.SYSTEM_ERROR.getCode());
        tResult.setMsg(msg);
        return tResult;
    }

    public static <T> Result<T> fail(Integer code, String msg) {
        Result<T> tResult = new Result<>();
        tResult.setCode(code);
        tResult.setMsg(msg);
        return tResult;
    }

    public static <T> Result<T> success() {
        Result<T> tResult = new Result<>();
        tResult.setCode(Constants.SUCCESS.getCode());
        tResult.setMsg(Constants.SUCCESS.getMsg());
        tResult.setSuccess(true);
        return tResult;
    }

    public static <T> Result<T> success(String msg) {
        Result<T> tResult = new Result<>();
        tResult.setCode(Constants.SUCCESS.getCode());
        tResult.setMsg(msg);
        tResult.setSuccess(true);
        return tResult;
    }

    public static <T> Result<T> success(T t) {
        Result<T> tResult = new Result<>();
        tResult.setCode(Constants.SUCCESS.getCode());
        tResult.setMsg("请求成功");
        tResult.setSuccess(true);
        tResult.setData(t);
        return tResult;
    }

    public static <T> Result<T> success(T t, String msg) {
        Result<T> tResult = new Result<>();
        tResult.setCode(Constants.SUCCESS.getCode());
        tResult.setMsg(msg);
        tResult.setSuccess(true);
        tResult.setData(t);
        return tResult;
    }
}
