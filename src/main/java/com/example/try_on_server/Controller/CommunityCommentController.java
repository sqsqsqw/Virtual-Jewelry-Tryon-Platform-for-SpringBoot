package com.example.try_on_server.Controller;

import com.example.try_on_server.Enums.ResultEnum;
import com.example.try_on_server.Respository.CommunityCommentRespository;
import com.example.try_on_server.Respository.UserRespository;
import com.example.try_on_server.Util.JSONManager;
import com.example.try_on_server.Util.ResultManager;
import com.example.try_on_server.domain.CommunityCommentDao;
import com.example.try_on_server.domain.Result;
import com.example.try_on_server.domain.UserDao;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.hibernate.type.descriptor.JdbcTypeNameMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping("/communitycomment")
public class CommunityCommentController {

    @Autowired
    CommunityCommentRespository communityCommentRespository;

    @Autowired
    UserRespository userRespository;

    @RequestMapping("/")
    public Result hello(){
        return ResultManager.success("hello, CommunityCommentController");
    }

    @PostMapping("/get")
    public Result getCommunityComment(@RequestBody HashMap<String, String> map){
        List<CommunityCommentDao> items = communityCommentRespository.findByCommunityDistributeID(Long.valueOf(map.get("did")));
        CommunityCommentDao comDao = null;
        List<CommunityCommentItem> res = new ArrayList<CommunityCommentItem>();
        CommunityCommentItem con = null;
        Iterator it = items.iterator();
        while(it.hasNext()){
            comDao = (CommunityCommentDao) it.next();
            con = new CommunityCommentItem();
            UserDao userDao = userRespository.getOne(comDao.getUserID());
            con.userName = userDao.getUserName();
            con.communityCommentContext = comDao.getCommunityCommentContext();
            con.communityCommentID = comDao.getCommunityCommentID();
            try {
                con.userAva = URLEncoder.encode(userDao.getUserAvatar(),"utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            con.userID = userDao.getUserId();
            res.add(con);
        }
        return ResultManager.success(res);
    }

    @PostMapping("/set")
    public Result setCommunityComment(@RequestBody HashMap<String, String> map){
        Long uid = Long.valueOf(map.get("userID"));
        String context = map.get("context");
        Long did = Long.valueOf(map.get("dID"));
        if(uid == 0) return ResultManager.error(ResultEnum.TOKEN_ERROR);
        CommunityCommentDao item = new CommunityCommentDao();
        item.setCommunityCommentContext(context);
        item.setCommunityCommentTime(new Date(System.currentTimeMillis()));
        item.setUserID(uid);
        item.setCommunityDistributeID(did);
        communityCommentRespository.save(item);
        return ResultManager.success();
    }

    class CommunityCommentItem{
        public Long communityCommentID;
        public Long userID;
        public String userAva;
        public String communityCommentContext;
        public String userName;
    }
}
