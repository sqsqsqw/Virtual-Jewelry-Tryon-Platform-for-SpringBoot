package com.example.try_on_server.Controller;

import com.example.try_on_server.Respository.GoodsCommentRespository;
import com.example.try_on_server.Respository.GoodsInfoRespository;
import com.example.try_on_server.Respository.UserCollectRespository;
import com.example.try_on_server.Respository.UserRespository;
import com.example.try_on_server.Util.ResultManager;
import com.example.try_on_server.domain.GoodsInfoDao;
import com.example.try_on_server.domain.Result;
import com.example.try_on_server.domain.UserCollectDao;
import com.example.try_on_server.domain.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static com.example.try_on_server.Enums.ResultEnum.OPERATION_INVALID;

@RestController
@RequestMapping("/usercollects")
public class UserCollectController {
    @Autowired
    UserCollectRespository userCollectRespository;
    @Autowired
    UserRespository userRespository;
    @Autowired
    GoodsInfoRespository goodsInfoRespository;

    @PostMapping("/")
    public Result hello () {
        return ResultManager.success("Hello UserCollect");
    }

    @PostMapping("/set")
    public Result setCollect(@RequestBody Map<String, String> map){
        String userId = map.get("userID");
        String goodId = map.get("goodID");
        System.out.println(userId + goodId);
        if(!userCollectRespository.findByUserIdAndGoodID(Long.valueOf(userId), Long.valueOf(goodId)).isEmpty()){
            return ResultManager.error(OPERATION_INVALID);
        }

        UserCollectDao item = new UserCollectDao();
        item.setUserId(Long.valueOf(userId));
        item.setGoodID(Long.valueOf(goodId));
        item.setCollectCreateTime(new Date(System.currentTimeMillis()));

        userCollectRespository.save(item);

        return ResultManager.success();
    }

    @PostMapping("/get")
    public Result getCollect(@RequestBody Map<String, String> map) throws UnsupportedEncodingException {
        String userId = map.get("userID");

        List<UserCollectDao> list = userCollectRespository.findByUserId(Long.valueOf(userId));
        List<CollectItem> items = new ArrayList<CollectItem>();
        Iterator it = list.iterator();
        while (it.hasNext()){
            UserCollectDao dao = (UserCollectDao)it.next();
            CollectItem item = new CollectItem();
            GoodsInfoDao good = goodsInfoRespository.getOne(dao.getGoodID());
            item.avaURL = URLEncoder.encode(good.getGoodImage(), "utf-8");
            item.goodName = good.getGoodDescription();
            item.shopName = userRespository.getOne(good.getSellerId()).getUserName();
            item.goodID = good.getGoodID();
            items.add(item);
        }

        return ResultManager.success(items);
    }

    class CollectItem {
        public String avaURL, shopName, goodName;
        public Long goodID;
    }

}
