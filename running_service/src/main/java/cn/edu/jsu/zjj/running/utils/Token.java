package cn.edu.jsu.zjj.running.utils;

import cn.edu.jsu.zjj.running.admin.entity.Admin;
import cn.edu.jsu.zjj.running.user.entity.User;

/**
 * 登录令牌
 */
public class Token {

    private User user;          //登录用户数据
    private Admin admin;        //登陆管理数据
    private String token;       //用户访问验证
    private Long endTime;       //token存活的最后时间

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }
}
