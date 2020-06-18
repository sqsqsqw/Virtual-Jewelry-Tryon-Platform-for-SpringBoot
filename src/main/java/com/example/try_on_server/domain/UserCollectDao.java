package com.example.try_on_server.domain;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "t_buss_collect")
public class UserCollectDao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "collect_id")
    private Long collectId;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "good_id")
    private Long goodID;
    @Column(name = "collect_create_time")
    private Date collectCreateTime;

    public Long getCollectId() {
        return collectId;
    }

    public void setCollectId(Long collectId) {
        this.collectId = collectId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getGoodID() {
        return goodID;
    }

    public void setGoodID(Long goodID) {
        this.goodID = goodID;
    }

    public Date getCollectCreateTime() {
        return collectCreateTime;
    }

    public void setCollectCreateTime(Date collectCreateTime) {
        this.collectCreateTime = collectCreateTime;
    }
}
