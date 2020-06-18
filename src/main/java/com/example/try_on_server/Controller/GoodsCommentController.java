package com.example.try_on_server.Controller;

import com.example.try_on_server.Respository.GoodsCommentRespository;
import com.example.try_on_server.Respository.UserRespository;
import com.example.try_on_server.Util.ResultManager;
import com.example.try_on_server.domain.GoodsCommentDao;
import com.example.try_on_server.domain.Result;
import com.example.try_on_server.domain.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping("/comment")
public class GoodsCommentController {

    @Autowired
    GoodsCommentRespository goodsCommentRespository;

    @Autowired
    UserRespository userRespository;

    @PostMapping("/get")
    public Result getGoodsComment(@RequestBody HashMap<String, String> map){
        Sort sort = Sort.by(Sort.Direction.DESC, "good_create_time");
        Long a = Long.valueOf(map.get("goodID"));
        List<GoodsCommentDao> res = goodsCommentRespository.findByGoodID(a);
        Iterator it = res.iterator();
        GoodsCommentDao dao;
        CommentItem item;
        UserDao user;
        List<CommentItem> lists = new ArrayList<CommentItem>();
        while(it.hasNext()){
            dao = (GoodsCommentDao)it.next();
            item = new CommentItem();
            user = userRespository.getOne(dao.getUserID());
            item.comm = dao.getGoodCommentContext();
            item.userName = user.getUserName();
            item.avaURL = user.getUserAvatar();
            lists.add(item);
        }
        return ResultManager.success(lists);
    }

    @PostMapping("/set")
    public Result setGoodsComment(@RequestBody HashMap<String, String> map){
        GoodsCommentDao item = new GoodsCommentDao();
        item.setGoodID(Long.valueOf(map.get("goodID")));
        item.setUserID(Long.valueOf(map.get("userID")));
        item.setGoodCommentTime(new Date(System.currentTimeMillis()));
        item.setGoodCommentContext(map.get("context"));
        goodsCommentRespository.save(item);
        return ResultManager.success();
    }

    class CommentItem{
        public String avaURL, userName, comm;

    }
}
