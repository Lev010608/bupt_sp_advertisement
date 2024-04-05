package com.example.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 公告信息表
*/
public class Course implements Serializable {
    private static final long serialVersionUID = 1L;

    /** ID */
    private Integer id;
    private String img;
    private String name;
    private String content;
    private String type;
    private String video;
    private String file;
    private String recommend;
    private Date recommendTime;
    private String addTime;
    private String channel;
    private String channelRcommend;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }



    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }


    public String getRecommend() {
        return recommend;
    }

    public void setRecommend(String recommend) {
        this.recommend = recommend;
    }

    public Date getRecommendTime() {return recommendTime;}

    public void setRecommendTime(Date recommendTime) {this.recommendTime = recommendTime;}

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getChannelRcommend() {return channelRcommend;}

    public void setChannelRcommend(String channelRcommend) {this.channelRcommend = channelRcommend;}

}