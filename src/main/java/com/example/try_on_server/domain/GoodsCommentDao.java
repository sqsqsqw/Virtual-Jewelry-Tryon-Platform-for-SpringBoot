package com.example.try_on_server.domain;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "t_buss_goods_comment")
public class GoodsCommentDao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "good_comment_id")
    private Long goodCommentID;
    @Column(name = "good_id")
    private Long goodID;
    @Column(name = "user_id")
    private Long userID;
    @Column(name = "good_comment_parent_id")
    private Long goodCommentParentID;
    @Column(name = "good_comment_context")
    private String goodCommentContext;
    @Column(name = "good_comment_time")
    private Date goodCommentTime;

    public Long getGoodCommentID() {
        return goodCommentID;
    }

    public void setGoodCommentID(Long goodCommentID) {
        this.goodCommentID = goodCommentID;
    }

    public Long getGoodID() {
        return goodID;
    }

    public void setGoodID(Long goodID) {
        this.goodID = goodID;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public Long getGoodCommentParentID() {
        return goodCommentParentID;
    }

    public void setGoodCommentParentID(Long goodCommentParentID) {
        this.goodCommentParentID = goodCommentParentID;
    }

    public String getGoodCommentContext() {
        return goodCommentContext;
    }

    public void setGoodCommentContext(String goodCommentContext) {
        this.goodCommentContext = goodCommentContext;
    }

    public Date getGoodCommentTime() {
        return goodCommentTime;
    }

    public void setGoodCommentTime(Date goodCommentTime) {
        this.goodCommentTime = goodCommentTime;
    }
}
