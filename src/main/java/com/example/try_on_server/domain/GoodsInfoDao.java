package com.example.try_on_server.domain;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "t_buss_goods_info")
public class GoodsInfoDao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "good_id")
    private Long goodID;                //商品id
    @Column(name = "good_class_id")
    private Long goodClassID;         //商品类别id
    @Column(name = "good_class_name")
    private String goodClassName;     //商品类别
    @Column(name = "good_image", columnDefinition = "varchar(512)")
    private String goodImage;          //商品照片
    @Column(name = "good_description", columnDefinition = "varchar(512)")
    private String goodDescription;    //商品描述
    @Column(name = "price")
    private Double price;                  //价格
    @Column(name = "good_create_time")
    private Date goodCreateTime;      //商品创建时间
    @Column(name = "good_url", columnDefinition = "varchar(512)")
    private String goodUrl;            //商品售卖地址
    @Column(name = "good_model_url", columnDefinition = "varchar(512)")
    private String goodModelUrl;      //商品模型地址
    @Column(name = "seller_id")
    private Long sellerId;              //卖家id

    public GoodsInfoDao() {
    }

    public Long getGoodID() {
        return goodID;
    }

    public void setGoodID(Long goodID) {
        this.goodID = goodID;
    }

    public Long getGoodClassID() {
        return goodClassID;
    }

    public void setGoodClassID(Long goodClassID) {
        this.goodClassID = goodClassID;
    }

    public String getGoodClassName() {
        return goodClassName;
    }

    public void setGoodClassName(String goodClassName) {
        this.goodClassName = goodClassName;
    }

    public String getGoodImage() {
        return goodImage;
    }

    public void setGoodImage(String goodImage) {
        this.goodImage = goodImage;
    }

    public String getGoodDescription() {
        return goodDescription;
    }

    public void setGoodDescription(String goodDescription) {
        this.goodDescription = goodDescription;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Date getGoodCreateTime() {
        return goodCreateTime;
    }

    public void setGoodCreateTime(Date goodCreateTime) {
        this.goodCreateTime = goodCreateTime;
    }

    public String getGoodUrl() {
        return goodUrl;
    }

    public void setGoodUrl(String goodUrl) {
        this.goodUrl = goodUrl;
    }

    public String getGoodModelUrl() {
        return goodModelUrl;
    }

    public void setGoodModelUrl(String goodModelUrl) {
        this.goodModelUrl = goodModelUrl;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }
}
