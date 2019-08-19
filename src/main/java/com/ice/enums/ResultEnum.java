package com.ice.enums;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author: ice
 * @create: 2019/3/18
 **/
@NoArgsConstructor
@AllArgsConstructor
@Accessors(fluent = false, chain = true, prefix = "")
public enum ResultEnum{

    SUCCESS(200, "SUCCESS"),
    SERVER_ERROR(500,"服务器内部错误"),
    TRIPARTITE_FAIL(501,"渠道接口返回失败"),
    TRIPARTITE_ERROR(502,"渠道接口调用出错");

    private Integer code;

    private String msg;

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String message){
        this.msg = message;
    }
}
