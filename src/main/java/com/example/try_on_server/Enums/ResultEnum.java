package com.example.try_on_server.Enums;

public enum ResultEnum {
    UNKNOW_ERROR(500,"未知错误"),
    ILLEGAL_PARAM(2000, "参数错误"),
    EXIST(2001,"此信息已存在"),
    NOT_EXIST(2002,"此信息不存在"),
    FILE_OPREATION_ERROR(2003,"文件操作错误"),
    FILE_NOT_FOUND(2004,"文件错误"),
    WRONG_PASSWD(2005,"密码错误"),
    ILLEGAL_FILE(2006,"非法文件"),
    TOKEN_ERROR(2007,"未登录"),
    VALUE_ERROR(2008,"传参错误"),
    AUTHORITY_ERROR(2009,"权限未许可"),
    OPERATION_INVALID(2010,"操作无效");
    private Integer code;

    private String msg;

    ResultEnum(Integer code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
