package com.example.try_on_server.Util;

import com.example.try_on_server.Enums.ResultEnum;
import com.example.try_on_server.domain.Result;

public class ResultManager {

    public static Result success(){
        return success("");
    }

    public static Result success(Object data){
        Result result = new Result();
        result.setCode(0);
        result.setMsg("成功");
        result.setData(data);
        return result;
    }

    public static Result error(){
        return error(ResultEnum.UNKNOW_ERROR);
    }

    public static Result error(ResultEnum resultEnum){
        Result result = new Result();
        result.setCode(resultEnum.getCode());
        result.setMsg(resultEnum.getMsg());
        result.setData(null);
        return result;
    }
}
