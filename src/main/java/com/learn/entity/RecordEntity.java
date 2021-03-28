package com.learn.entity;

import java.io.Serializable;
import java.util.Date;

import com.learn.service.*;


/**
 * 作业记录
 */
public class RecordEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //
    private Long id;

    //作业
    private Long zuoye;

    private ZuoyeEntity zuoyeEntity;

    public ZuoyeEntity getZuoyeEntity() {
        return zuoyeEntity;
    }

    public void setZuoyeEntity(ZuoyeEntity zuoyeEntity) {
        this.zuoyeEntity = zuoyeEntity;
    }

    //学生
    private Long user;

    private SysUserEntity sysUserEntity;

    public SysUserEntity getSysUserEntity() {
        return sysUserEntity;
    }

    public void setSysUserEntity(SysUserEntity sysUserEntity) {
        this.sysUserEntity = sysUserEntity;
    }


    //我的作业
    private String content;

    //附件
    private String url;

    //分数
    private String score;

    //评价
    private String comment;

    //添加时间
    private Date gmttime = new Date();


    /**
     * 设置：
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取：
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置：作业
     */
    public void setZuoye(Long zuoye) {
        this.zuoye = zuoye;
    }

    /**
     * 获取：作业
     */
    public Long getZuoye() {
        return zuoye;
    }

    /**
     * 设置：学生
     */
    public void setUser(Long user) {
        this.user = user;
    }

    /**
     * 获取：学生
     */
    public Long getUser() {
        return user;
    }

    /**
     * 设置：我的作业
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取：我的作业
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置：附件
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 获取：附件
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置：分数
     */
    public void setScore(String score) {
        this.score = score;
    }

    /**
     * 获取：分数
     */
    public String getScore() {
        return score;
    }

    /**
     * 设置：评价
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * 获取：评价
     */
    public String getComment() {
        return comment;
    }

    /**
     * 设置：添加时间
     */
    public void setGmttime(Date gmttime) {
        this.gmttime = gmttime;
    }

    /**
     * 获取：添加时间
     */
    public Date getGmttime() {
        return gmttime;
    }
}
