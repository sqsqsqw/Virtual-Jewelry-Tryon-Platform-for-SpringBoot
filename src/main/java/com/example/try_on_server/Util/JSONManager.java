package com.example.try_on_server.Util;

import java.io.IOException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Hashqi
 * 操作json的封装方法
 * use:jackson
 */
public class JSONManager {
    /*
     * 001.json转换成对象
     */
    public static Object jsonToObj(Object obj, String jsonStr)  {
        ObjectMapper mapper = new ObjectMapper();
        try {
            obj = mapper.readValue(jsonStr, obj.getClass());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return obj;
    }

    /*
     * 002.对象转换成json
     */
    public static String objToJson(Object obj){
        ObjectMapper mapper = new ObjectMapper();
        String res = "";
        try {
            res = mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return res;
    }
}

