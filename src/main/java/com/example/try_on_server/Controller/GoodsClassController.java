package com.example.try_on_server.Controller;


import com.example.try_on_server.Respository.GoodsClassRespository;
import com.example.try_on_server.Util.ResultManager;
import com.example.try_on_server.domain.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/class")
public class GoodsClassController {
    @Autowired
    GoodsClassRespository goodsClassRespository;

    @PostMapping("/gets")
    public Result getClasses(){
        return ResultManager.success(goodsClassRespository.findAll());
    }

    @PostMapping("/get")
    public Result getClass(@RequestBody HashMap<String, String> map){
        return ResultManager.success(goodsClassRespository.findByClassName(map.get("className")));
    }

}
