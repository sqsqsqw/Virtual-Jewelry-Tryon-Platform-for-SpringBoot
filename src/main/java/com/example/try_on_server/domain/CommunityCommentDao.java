package com.example.try_on_server.domain;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "t_buss_community_comment")
public class CommunityCommentDao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "community_comment_id")
    private Long communityCommentID;
    @Column(name = "user_id")
    private Long userID;
    @Column(name = "community_comment_parent_id")
    private Long communityCommentParentID;
    @Column(name = "community_comment_context")
    private String communityCommentContext;
    @Column(name = "community_comment_time")
    private Date communityCommentTime;
    @Column(name = "community_distribute_id")
    private Long communityDistributeID;

    public Long getCommunityCommentID() {
        return communityCommentID;
    }

    public void setCommunityCommentID(Long communityCommentID) {
        this.communityCommentID = communityCommentID;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public Long getCommunityCommentParentID() {
        return communityCommentParentID;
    }

    public void setCommunityCommentParentID(Long communityCommentParentID) {
        this.communityCommentParentID = communityCommentParentID;
    }

    public String getCommunityCommentContext() {
        return communityCommentContext;
    }

    public void setCommunityCommentContext(String communityCommentContext) {
        this.communityCommentContext = communityCommentContext;
    }

    public Date getCommunityCommentTime() {
        return communityCommentTime;
    }

    public void setCommunityCommentTime(Date communityCommentTime) {
        this.communityCommentTime = communityCommentTime;
    }

    public Long getCommunityDistributeID() {
        return communityDistributeID;
    }

    public void setCommunityDistributeID(Long communityDistributeID) {
        this.communityDistributeID = communityDistributeID;
    }
}
