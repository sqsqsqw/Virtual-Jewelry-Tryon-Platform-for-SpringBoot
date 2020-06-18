package com.example.try_on_server.Controller;

import com.example.try_on_server.Respository.*;
import com.example.try_on_server.domain.HomepageInfoDao;
import com.example.try_on_server.domain.RoleDao;
import com.example.try_on_server.domain.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;

@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    private UserRespository userRespository;
    @Autowired
    private RoleRespository roleRespository;
    @Autowired
    private UserCollectRespository userCollectRespository;
    @Autowired
    private HomepageInfoRespository homepageInfoRespository;
    @Autowired
    private GoodsInfoRespository goodsInfoRespository;
    @Autowired
    private GoodsCommentRespository goodsCommentRespository;
    @Autowired
    private GoodsClassRespository goodsClassRespository;
    @Autowired
    private CommunityCommentRespository communityCommentRespository;
    @Autowired
    private CommunityDistributeRespository communityDistributeRespository;

    @RequestMapping("/")
    public void start(){
        user();
        role();
        homepageInfo();

    }
    private void user(){
        UserDao userDao = new UserDao();
        userDao.setRoleID(1L);
        userDao.setUserAvatar("avatar/init.jpg");
        userDao.setUserCreateTime(new Date(2020,04,12));
        userDao.setEmail("hashqishen@163.com");
        userDao.setUserName("Hashqi");
        userDao.setUserPhone(18742054007L);
        userDao.setUserPwd("root");
        userDao.setUserSex("male");
        userRespository.save(userDao);
    }
    private void role(){
        RoleDao roleDao= new RoleDao();
        roleDao.setRoleName("普通用户");
        roleRespository.save(roleDao);
        roleDao = new RoleDao();
        roleDao.setRoleName("店铺");
        roleRespository.save(roleDao);
    }
    private void homepageInfo(){
        HomepageInfoDao homepageInfoDao = new HomepageInfoDao();
        homepageInfoDao.setHomepageImage("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1586336183427&di=03d5939311234c26abf076f90ec1a1d8&imgtype=0&src=http%3A%2F%2Fimg2.imgtn.bdimg.com%2Fit%2Fu%3D1530807297%2C3170041459%26fm%3D214%26gp%3D0.jpg");
        homepageInfoDao.setHomepageState("1");
        homepageInfoDao.setHomepageTitle("冬季帽子女冬围巾套装韩版");
        homepageInfoDao.setHomepageGoodId(1L);
        homepageInfoRespository.save(homepageInfoDao);
    }
}
