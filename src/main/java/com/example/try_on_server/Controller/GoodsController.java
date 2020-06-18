package com.example.try_on_server.Controller;

import com.example.try_on_server.Enums.ResultEnum;
import com.example.try_on_server.Respository.GoodsClassRespository;
import com.example.try_on_server.Respository.GoodsInfoRespository;
import com.example.try_on_server.Respository.RoleRespository;
import com.example.try_on_server.Respository.UserRespository;
import com.example.try_on_server.Util.ResultManager;
import com.example.try_on_server.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.Date;
import java.util.*;

import static com.example.try_on_server.Enums.ResultEnum.AUTHORITY_ERROR;
import static com.example.try_on_server.Enums.ResultEnum.NOT_EXIST;
import static com.example.try_on_server.Enums.ResultEnum.TOKEN_ERROR;

@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    GoodsInfoRespository goodsInfoRespository;

    @Autowired
    GoodsClassRespository goodsClassRespository;

    @Autowired
    UserRespository userRespository;

    @Autowired
    RoleRespository roleRespository;


    @RequestMapping("/")
    public Result hello(){
        return ResultManager.success("hello, CommunityDistributeController");
    }

    @PostMapping("/get/info")
    public Result getInfo(@RequestBody HashMap<String, String> map){
        if(map.get("userID") == null || map.get("userID") == "" ){
            return ResultManager.error(TOKEN_ERROR);
        }
        Long a = Long.valueOf(map.get("goodID"));
        GoodsInfoDao item = goodsInfoRespository.findByGoodID(a).get(0);
        if(item.getGoodID() == null ){
            return ResultManager.error(NOT_EXIST);
        }
        return ResultManager.success(item);
    }

    @PostMapping("/get/list")
    public Result getList(){
        Sort sort = Sort.by(Sort.Direction.DESC, "good_create_time");
        List<GoodsInfoDao> item = goodsInfoRespository.findAll(sort);
        return ResultManager.success(item);
    }

    @PostMapping("/get/sort")
    public Result getSort(@RequestBody HashMap<String, String> map){
        Sort sort = Sort.by(Sort.Direction.DESC, "good_create_time");
        List<GoodsInfoDao> item = goodsInfoRespository.findByGoodClassID(Long.valueOf(map.get("cid")) ,sort);
        return ResultManager.success(item);
    }

    @PostMapping("/set")
    public Result setInfo(@RequestBody HashMap<String, String> map){
        if(map.get("uid") == null || map.get("uid") == "" ){
            return ResultManager.error(TOKEN_ERROR);
        }
        UserDao user = userRespository.getOne(Long.valueOf(map.get("uid")));
        if(!roleRespository.getOne(user.getRoleID()).getRoleName().equals("店铺")){
            return ResultManager.error(AUTHORITY_ERROR);
        }
        GoodsInfoDao item = new GoodsInfoDao();
        GoodsClassDao c = goodsClassRespository.getOne(Long.valueOf(map.get("cid")));
        item.setGoodClassID(c.getGoodClassID());
        item.setGoodClassName(c.getClassName());
        item.setGoodCreateTime(new Date(System.currentTimeMillis()));
        item.setGoodDescription(map.get("des"));
        try {
            item.setGoodImage(URLDecoder.decode(map.get("img"),"utf-8"));
            item.setGoodModelUrl(URLDecoder.decode(map.get("murl"),"utf-8"));
            item.setGoodUrl(URLDecoder.decode(map.get("url"),"utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        item.setPrice(Double.valueOf(map.get("price")));
        item.setSellerId(Long.valueOf(map.get("uid")));
        goodsInfoRespository.save(item);
        return ResultManager.success();
    }

    @PostMapping("/getmodel")
    public Result getModel(@RequestBody String goodID){
        if(goodID == null ){
            return ResultManager.error(ResultEnum.ILLEGAL_PARAM);
        }
        Long id = Long.valueOf(goodID.substring(goodID.indexOf('=') + 1));
        Map<String, String> res = new HashMap<>();
        GoodsInfoDao good = goodsInfoRespository.getOne(id);
        List<GoodsClassDao> clss = goodsClassRespository.findByClassName(good.getGoodClassName());
        if(clss.isEmpty())  return ResultManager.error(ResultEnum.UNKNOW_ERROR);
        GoodsClassDao cls = clss.get(0);
        res.put("modelURL", good.getGoodModelUrl());
        res.put("xNum", cls.getClassX());
        res.put("yNum", cls.getClassY());
        res.put("zNum", cls.getClassZ());
        res.put("scaleNum", cls.getClassScale());
        return ResultManager.success(res);
    }

    @PostMapping("/get/shoplist")
    public Result getShoplist(@RequestBody HashMap<String, String> map){
        if(map.get("userID") == null ){
            return ResultManager.error(ResultEnum.ILLEGAL_PARAM);
        }
        List<GoodsInfoDao> res = goodsInfoRespository.findBySellerId(Long.valueOf(map.get("userID")));
        return ResultManager.success(res);
    }


    @PostMapping("/img")
    public Result image(@RequestParam("img") MultipartFile img,
                         @RequestParam("name") String name){
        if(img.isEmpty()) return ResultManager.error(ResultEnum.VALUE_ERROR);
        String fileName = img.getOriginalFilename();
        try{
            File path = new File(".\\image\\" + name + "\\" );
            path.mkdirs();
            BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(new File(".\\image\\" + name + "\\" + fileName)));
            out.write(img.getBytes());
            out.flush();
            out.close();
        } catch (FileNotFoundException e) {
            return ResultManager.error(ResultEnum.FILE_NOT_FOUND);

        } catch (IOException e) {
            return ResultManager.error(ResultEnum.FILE_OPREATION_ERROR);
        }
        String res = "/image/" + name + "/" + fileName;
        ReUrl reUrl = new ReUrl();
        try {
            reUrl.url = URLEncoder.encode(res, "utf-8");
            return ResultManager.success(reUrl);
        } catch (UnsupportedEncodingException e) {
            return ResultManager.error(ResultEnum.UNKNOW_ERROR);
        }
    }

    @PostMapping("/model")
    public Result model(@RequestParam("model") MultipartFile model,
                         @RequestParam("name") String name){
        if(model.isEmpty()) return ResultManager.error(ResultEnum.VALUE_ERROR);
        String fileName = model.getOriginalFilename();
        try{
            File path = new File(".\\model\\" + name + "\\" );
            path.mkdirs();
            BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(new File(".\\model\\" + name + "\\" + fileName)));
            out.write(model.getBytes());
            out.flush();
            out.close();
        } catch (FileNotFoundException e) {
            return ResultManager.error(ResultEnum.FILE_NOT_FOUND);

        } catch (IOException e) {
            return ResultManager.error(ResultEnum.FILE_OPREATION_ERROR);
        }
        String res = "/model/" + name + "/" + fileName;
        ReUrl reUrl = new ReUrl();
        try {
            reUrl.url = URLEncoder.encode(res, "utf-8");
            return ResultManager.success(reUrl);
        } catch (UnsupportedEncodingException e) {
            return ResultManager.error(ResultEnum.UNKNOW_ERROR);
        }
    }


    @PostMapping("/search")
    public Result search(@RequestBody Map<String, String> map) throws UnsupportedEncodingException {
        String str = map.get("str");

        List<GoodsInfoDao> list = goodsInfoRespository.findByGoodDescriptionLike( "%" + str + "%");
        Iterator it = list.iterator();
        List<SearchItem> items = new ArrayList<SearchItem>();
        while (it.hasNext()){
            GoodsInfoDao dao = (GoodsInfoDao)it.next();
            SearchItem item = new SearchItem();
            UserDao user = userRespository.getOne(dao.getSellerId());
            item.avaURL = URLEncoder.encode(dao.getGoodImage(), "utf-8");
            item.goodName = dao.getGoodDescription();
            item.shopName = user.getUserName();
            item.goodID = dao.getGoodID();
            items.add(item);
        }
        return ResultManager.success(items);
    }


}
class ReUrl{
    public String url;
}
class SearchItem {
    public String avaURL, shopName, goodName;
    public Long goodID;
}
