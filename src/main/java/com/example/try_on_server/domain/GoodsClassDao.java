package com.example.try_on_server.domain;

import javax.persistence.*;

@Entity
@Table(name = "t_base_goods_class")
public class GoodsClassDao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "good_class_id")
    private Long goodClassID;      //商品类别id
    @Column(name = "class_name", columnDefinition = "varchar(32)")
    private String className;      //商品类别
    @Column(name = "class_x", columnDefinition = "varchar(32)")
    private String classX;
    @Column(name = "class_y", columnDefinition = "varchar(32)")
    private String classY;
    @Column(name = "class_z", columnDefinition = "varchar(32)")
    private String classZ;
    @Column(name = "class_scale", columnDefinition = "varchar(32)")
    private String classScale;

    public Long getGoodClassID() {
        return goodClassID;
    }

    public void setGoodClassID(Long goodClassID) {
        this.goodClassID = goodClassID;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassX() {
        return classX;
    }

    public void setClassX(String classX) {
        this.classX = classX;
    }

    public String getClassY() {
        return classY;
    }

    public void setClassY(String classY) {
        this.classY = classY;
    }

    public String getClassZ() {
        return classZ;
    }

    public void setClassZ(String classZ) {
        this.classZ = classZ;
    }

    public String getClassScale() {
        return classScale;
    }

    public void setClassScale(String classScale) {
        this.classScale = classScale;
    }
}
