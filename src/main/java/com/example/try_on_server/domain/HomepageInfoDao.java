package com.example.try_on_server.domain;

import javax.persistence.*;

@Entity
@Table(name = "t_buss_homepage_info")
public class HomepageInfoDao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "homepage_id")
    private Long homepageID;            //主页id
    @Column(name = "homepage_title")
    private String homepageTitle;      //主页标题
    @Column(name = "homepage_image", columnDefinition = "varchar(512)")
    private String homepageImage;      //主页图片
    @Column(name = "homepage_good_id")
    private Long homepageGoodId;        //主页地址
    @Column(name = "homepage_state")
    private String homepageState;      //主页状态

    public Long getHomepageID() {
        return homepageID;
    }

    public void setHomepageID(Long homepageID) {
        this.homepageID = homepageID;
    }

    public String getHomepageTitle() {
        return homepageTitle;
    }

    public void setHomepageTitle(String homepageTitle) {
        this.homepageTitle = homepageTitle;
    }

    public String getHomepageImage() {
        return homepageImage;
    }

    public void setHomepageImage(String homepageImage) {
        this.homepageImage = homepageImage;
    }


    public Long getHomepageGoodId() {
        return homepageGoodId;
    }

    public void setHomepageGoodId(Long homepageGoodId) {
        this.homepageGoodId = homepageGoodId;
    }

    public String getHomepageState() {
        return homepageState;
    }

    public void setHomepageState(String homepageState) {
        this.homepageState = homepageState;
    }
}
