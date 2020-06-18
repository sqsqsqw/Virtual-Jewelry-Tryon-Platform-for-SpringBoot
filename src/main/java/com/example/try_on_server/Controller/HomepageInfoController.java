package com.example.try_on_server.Controller;

import com.example.try_on_server.Respository.GoodsInfoRespository;
import com.example.try_on_server.Respository.HomepageInfoRespository;
import com.example.try_on_server.Respository.UserRespository;
import com.example.try_on_server.Util.JSONManager;
import com.example.try_on_server.Util.ResultManager;
import com.example.try_on_server.domain.HomepageInfoDao;
import com.example.try_on_server.domain.Result;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import static com.example.try_on_server.Enums.ResultEnum.TOKEN_ERROR;

@RestController
@RequestMapping("/homepageinfo")
public class HomepageInfoController {
    @Autowired
    private HomepageInfoRespository homepageInfoRespository;
    @Autowired
    private GoodsInfoRespository goodsInfoRespository;

    private final static Logger logger = LoggerFactory.getLogger(UserController.class);


    @RequestMapping("/")
    public Result hello(){
        return ResultManager.success("hello, HomepageInfoController");
    }

    @RequestMapping("/get")
    public Result getHomepage(){
        List<HomepageInfoDao> infos = homepageInfoRespository.findByHomepageState("1");
        try {
            if(infos.size() != 0){
                for(int i = 0; i < infos.size(); i++){
                    HomepageInfoDao tmp = infos.get(i);
                    tmp.setHomepageImage(URLEncoder.encode(tmp.getHomepageImage(), "utf-8"));
                    infos.set(i, tmp);
                }
                //URLEncoder.encode
                return ResultManager.success(infos);
            }
            else
                return ResultManager.success();

        } catch (Exception e) {
            e.printStackTrace();
            return ResultManager.error();
        }
    }


    @RequestMapping("/set")
    public Result setHomepage(@RequestBody HashMap<String, String> map) {
        if(map.get("uid") == null || map.get("uid") == "" ){
            return ResultManager.error(TOKEN_ERROR);
        }

        String title = map.get("title");
        String gid = map.get("goodID");
        List<HomepageInfoDao> items = homepageInfoRespository.findByHomepageState("1");
        Iterator<HomepageInfoDao> iter = items.iterator();
        while (iter.hasNext()) {
            HomepageInfoDao info = (HomepageInfoDao) iter.next();
            info.setHomepageState("0");
            homepageInfoRespository.save(info);
        }
        String image = goodsInfoRespository.getOne(Long.valueOf(gid)).getGoodImage();

        HomepageInfoDao homepageInfoDao = new HomepageInfoDao();
        homepageInfoDao.setHomepageImage(image);
        homepageInfoDao.setHomepageGoodId(Long.valueOf(gid));
        homepageInfoDao.setHomepageState("1");
        homepageInfoDao.setHomepageTitle(title);
        homepageInfoRespository.save(homepageInfoDao);

        return ResultManager.success();
    }
}
