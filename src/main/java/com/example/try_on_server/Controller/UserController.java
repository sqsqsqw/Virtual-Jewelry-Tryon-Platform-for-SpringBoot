package com.example.try_on_server.Controller;

import com.example.try_on_server.Enums.ResultEnum;
import com.example.try_on_server.Exception.ResultException;
import com.example.try_on_server.Respository.GoodsInfoRespository;
import com.example.try_on_server.Respository.RoleRespository;
import com.example.try_on_server.Respository.UserRespository;
import com.example.try_on_server.Util.EncryptionMD5Manager;
import com.example.try_on_server.Util.JSONManager;
import com.example.try_on_server.Util.ResultManager;
import com.example.try_on_server.domain.GoodsInfoDao;
import com.example.try_on_server.domain.Result;
import com.example.try_on_server.domain.UserDao;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URLEncoder;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import static com.example.try_on_server.Enums.ResultEnum.ILLEGAL_PARAM;
import static com.example.try_on_server.Enums.ResultEnum.OPERATION_INVALID;

@RestController
@JsonIgnoreProperties(value = { "hibernateLazyInitializer"})
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRespository userRespository;
    @Autowired
    private RoleRespository roleRespository;
    @Autowired
    private GoodsInfoRespository goodsInfoRespository;

    private final static Logger logger = LoggerFactory.getLogger(UserController.class);

    @PostMapping("/")
    public Result hello () {
        return ResultManager.success("Hello User");
    }

    @PostMapping("/login")
    public Result login (@RequestBody HashMap<String, String> map)
            throws Exception{
        String name = map.get("name");
        String passwd = map.get("passwd");
        List<UserDao> userList = userRespository.findByUserName(name);
        if(userList.isEmpty()) return ResultManager.error(ResultEnum.NOT_EXIST);  //用户不存在
        if(passwd.length() < 20) return ResultManager.error(ILLEGAL_PARAM);  //密码长度不符合
        UserDao user = userList.get(0);
        if(user.getUserPwd().equals(passwd)){
            return ResultManager.success(user);
        }

        return ResultManager.error(ResultEnum.WRONG_PASSWD);
    }

    @PostMapping("/reg")
    private Result register (@RequestBody HashMap<String, String> map){

        String name = map.get("name");
        String email = "";
        String passwd = map.get("passwd");
        String sex = "";
        String phone = "";

        List<UserDao> userList = userRespository.findByUserName(name);
        if(!userList.isEmpty()) return ResultManager.error(ResultEnum.EXIST);  //用户已存在
        UserDao user = new UserDao();
        user.setUserEmail(email);
        if(passwd.length() > 20)  return ResultManager.error(ILLEGAL_PARAM);  //密码长度不符合
        //if(!user.setUser_pwd(passwd);) return ResultManager.error(ResultEnum.UNKNOW_ERROR); //md5加密错误
        user.setUserPwd(passwd);
        user.setUserName(name);
        user.setUserAvatar("/avatar/init.jpg");
        user.setUserPhone(0L);
        user.setEmail(email);
        user.setUserSex(sex);
        user.setRoleID(1L);
        Date now = new Date(System.currentTimeMillis());
        user.setUserCreateTime(now);

        userRespository.save(user);
        return ResultManager.success();
    }
    @PostMapping("/find")
    public Result find (@RequestParam("id") int a) {
        Long id = new Long(a);
        UserDao user;
        try {
            user = userRespository.getOne(id);
        }
        catch (Exception e) {
            return ResultManager.error(ResultEnum.NOT_EXIST);  //用户不存在
        }
        return ResultManager.success(user.toString());
    }
    @PostMapping("/avatar")
    public Result avatar(@RequestParam("avatar") MultipartFile avatar,
                         @RequestParam("userId") int id){
        if(avatar.isEmpty()) return ResultManager.error(ResultEnum.VALUE_ERROR);
        Long userID = Long.valueOf(id);
        String fileName = avatar.getOriginalFilename();
        String name = userRespository.getOne(new Long(id)).getUserName();
        try{
            File path = new File(".\\avatar\\" + name + "\\" );
            path.mkdirs();
            BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(new File(".\\avatar\\" + name + "\\" + fileName)));
            out.write(avatar.getBytes());
            out.flush();
            out.close();
            UserDao userDao = userRespository.getOne(userID);
            userDao.setUserAvatar("/avatar/" + name + "/" + fileName);
            userRespository.save(userDao);
        } catch (FileNotFoundException e) {
            logger.info(e.getMessage());
            return ResultManager.error(ResultEnum.FILE_NOT_FOUND);

        } catch (IOException e) {
            logger.info(e.getMessage());
            return ResultManager.error(ResultEnum.FILE_OPREATION_ERROR);
        }
        return ResultManager.success("/avatar/" + name + "/" + fileName);
    }

    @PostMapping("/role")
    public Result getRole(@RequestBody HashMap<String, String> map){
        return ResultManager.success(roleRespository.findByRoleID(Long.valueOf(map.get("rid"))));
    }

    @PostMapping("/upgrade")
    public Result upgrade(@RequestBody HashMap<String, String> map){
        String id = map.get("userId");
        UserDao userDao = userRespository.getOne(Long.valueOf(id));
        if(userDao.getRoleID() == 1){
            userDao.setRoleID(2L);
            userRespository.save(userDao);
            return ResultManager.success();
        }
        else{
            return ResultManager.error(OPERATION_INVALID);
        }
    }
    @PostMapping("/getshop")
    public Result getShop(){
        Long roleID = roleRespository.findByRoleName("店铺").get(0).getRoleID();
        List<UserDao> shops = userRespository.findByRoleID(roleID);
        List<ShopItem> res = new ArrayList<ShopItem>();
        ShopItem shop = new ShopItem();

        Iterator it = shops.iterator();
        while(it.hasNext()){
            shop = new ShopItem();
            UserDao tmp = (UserDao)it.next();
            List<GoodsInfoDao> infos = goodsInfoRespository.findBySellerId(tmp.getUserId());
            shop.shopID = tmp.getUserId();
            shop.name = tmp.getUserName();
            shop.ava = tmp.getUserAvatar();
            int length = infos.size() >= 6 ? 6 : infos.size();
            shop.imgs = new ArrayList<String>();
            for(int i = 0; i < length; i++){
                try {
                    shop.imgs.add(URLEncoder.encode(infos.get(i).getGoodImage(), "utf-8"));
                } catch (UnsupportedEncodingException e) {
                    return ResultManager.error();
                }
            }
            if(length != 0){
                res.add(shop);
            }
        }
        return ResultManager.success(res);
    }

    @PostMapping("/getShopInfo")
    public Result getShopInfo(@RequestBody HashMap<String, String> map){
        Long userID = Long.valueOf(map.get("userID"));
        UserDao shopInfo = userRespository.getOne(userID);
        ShopItem item = new ShopItem();
        item.ava = shopInfo.getUserAvatar();
        item.name = shopInfo.getUserName();
        item.shopID = shopInfo.getUserId();
        return ResultManager.success(item);
    }


    @PostMapping("/change")
    public Result change (@RequestBody HashMap<String, String> map) {
        String key = map.get("key");
        String val = map.get("val");
        String userIDStr = map.get("uid");
        Long userID = Long.valueOf(userIDStr);
        UserDao userDao = userRespository.getOne(userID);
        switch (key){
            case "用户名":
                userDao.setUserName(val);
                break;
            case "性别":
                userDao.setUserSex(val);
                break;
            case "电话":
                userDao.setUserPhone(Long.valueOf(val));
                break;
            case "邮箱":
                if(userDao.setEmail(val))
                    return ResultManager.error(ILLEGAL_PARAM);
                break;
            default:
                return ResultManager.error(OPERATION_INVALID);
        }
        userRespository.save(userDao);
        return ResultManager.success();
    }

    class ShopItem{
        public Long shopID;
        public String ava, name;
        public List<String> imgs;
    }

}
