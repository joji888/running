package cn.edu.jsu.zjj.running.comment.entity;

import cn.edu.jsu.zjj.running.user.entity.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.io.Serializable;

/**
 * 评论表(Comment)实体类
 *
 * @author PL
 * @since 2021-12-26 10:52:11
 */
public class Comment implements Serializable {
    private static final long serialVersionUID = 517193928100159592L;
    /**
     * 评论ID
     */
    private Integer cId;
    /**
     * 订单ID
     */
    private Integer oId;
    /**
     * 用户ID
     */
    private Integer uId;
    /**
     * 评论时间
     */
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm",timezone="GMT+8")
    private Date cTime;
    /**
     * 差好评
     */
    private String cType;
    /**
     * 评论内容
     */
    private String cContent;

    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getCId() {
        return cId;
    }

    public void setCId(Integer cId) {
        this.cId = cId;
    }

    public Integer getOId() {
        return oId;
    }

    public void setOId(Integer oId) {
        this.oId = oId;
    }

    public Integer getUId() {
        return uId;
    }

    public void setUId(Integer uId) {
        this.uId = uId;
    }

    public Date getCTime() {
        return cTime;
    }

    public void setCTime(Date cTime) {
        this.cTime = cTime;
    }

    public String getCType() {
        return cType;
    }

    public void setCType(String cType) {
        this.cType = cType;
    }

    public String getCContent() {
        return cContent;
    }

    public void setCContent(String cContent) {
        this.cContent = cContent;
    }

}

