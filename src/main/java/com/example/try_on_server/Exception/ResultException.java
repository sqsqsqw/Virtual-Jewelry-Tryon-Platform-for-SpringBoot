package com.example.try_on_server.Exception;

import com.example.try_on_server.Enums.ResultEnum;

public class ResultException extends Exception {

    private ResultEnum result;
    public ResultException(ResultEnum result ,String message) {
        super(message);
        this.result = result;
    }

    public ResultEnum getResult() {
        return result;
    }
}
