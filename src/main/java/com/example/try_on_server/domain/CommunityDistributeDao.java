package com.example.try_on_server.domain;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "t_buss_community_distribute")
public class CommunityDistributeDao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "community_distribute_id")
    private Long communityDistributeID;
    @Column(name = "community_distribute_title")
    private String communityDistributeTitle;
    @Column(name = "community_distribute_context", columnDefinition = "varchar(512)")
    private String communityDistributeContext;
    @Column(name = "user_id")
    private Long userID;
    @Column(name = "good_id")
    private Long goodID;
    @Column(name = "community_distribute_create_time")
    private Date communityDistributeCreateTime;

    public Long getCommunityDistributeID() {
        return communityDistributeID;
    }

    public void setCommunityDistributeID(Long communityDistributeID) {
        this.communityDistributeID = communityDistributeID;
    }

    public String getCommunityDistributeTitle() {
        return communityDistributeTitle;
    }

    public void setCommunityDistributeTitle(String communityDistributeTitle) {
        this.communityDistributeTitle = communityDistributeTitle;
    }

    public String getCommunityDistributeContext() {
        return communityDistributeContext;
    }

    public void setCommunityDistributeContext(String communityDistributeContext) {
        this.communityDistributeContext = communityDistributeContext;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public Long getGoodID() {
        return goodID;
    }

    public void setGoodID(Long goodID) {
        this.goodID = goodID;
    }

    public Date getCommunityDistributeCreateTime() {
        return communityDistributeCreateTime;
    }

    public void setCommunityDistributeCreateTime(Date communityDistributeCreateTime) {
        this.communityDistributeCreateTime = communityDistributeCreateTime;
    }
}
