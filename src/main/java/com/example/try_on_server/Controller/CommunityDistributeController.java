package com.example.try_on_server.Controller;

import com.example.try_on_server.Respository.CommunityCommentRespository;
import com.example.try_on_server.Respository.CommunityDistributeRespository;
import com.example.try_on_server.Respository.UserRespository;
import com.example.try_on_server.Util.JSONManager;
import com.example.try_on_server.Util.ResultManager;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.domain.Sort;
import com.example.try_on_server.domain.CommunityDistributeDao;
import com.example.try_on_server.domain.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import static com.example.try_on_server.Enums.ResultEnum.NOT_EXIST;

@RestController
@RequestMapping("/communityDistribute")
public class CommunityDistributeController {

    @Autowired
    CommunityDistributeRespository communityDistributeRespository;
    @Autowired
    UserRespository userRespository ;

    @RequestMapping("/")
    public Result hello(){
        return ResultManager.success("hello, CommunityDistributeController");
    }

    @PostMapping("/getInfo")
    public Result getCommunityCommentInfo(@RequestBody HashMap<String, String> map){
        Long id = Double.valueOf(map.get("id")).longValue();
        CommunityDistributeDao communityDistributeDao = communityDistributeRespository.findByCommunityDistributeID(id).get(0);
        if(communityDistributeDao == null){
            return ResultManager.error(NOT_EXIST);
        }
        return ResultManager.success(communityDistributeDao);
    }
    @PostMapping("/get")
    public Result getCommunityComment(){
        Sort sort=Sort.by(Sort.Direction.DESC,"community_distribute_create_time");
        List<CommunityDistributeDao> items = communityDistributeRespository.findAll();
        List<Contacts> res = new ArrayList<Contacts>();
        Iterator it = items.iterator();
        while(it.hasNext()){
            CommunityDistributeDao item = (CommunityDistributeDao)it.next();
            Contacts contacts = new Contacts();
            contacts.name = item.getCommunityDistributeTitle();
            contacts.message = item.getCommunityDistributeContext();
            contacts.imageUrl = userRespository.getOne(item.getUserID()).getUserAvatar();
            contacts.ID = item.getCommunityDistributeID();
            res.add(contacts);
        }
        return ResultManager.success(res);
    }


    @PostMapping("/set")
    public Result setCommunityComment(@RequestBody HashMap<String, String> map){
        CommunityDistributeDao item = new CommunityDistributeDao();
        item.setCommunityDistributeContext(map.get("context"));
        item.setUserID(Long.valueOf(map.get("userID")));
        item.setGoodID(Long.valueOf(map.get("goodID")));
        item.setCommunityDistributeTitle(map.get("title"));
        Date now = new Date(System.currentTimeMillis());
        item.setCommunityDistributeCreateTime(now);
        communityDistributeRespository.save(item);
        return ResultManager.success();
    }

    class Contacts {
        public String name, message;
        public String imageUrl;
        public Long ID;
    }
}
