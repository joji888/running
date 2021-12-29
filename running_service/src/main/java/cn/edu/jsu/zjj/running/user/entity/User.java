package cn.edu.jsu.zjj.running.user.entity;

import java.io.Serializable;

/**
 * 用户表(User)实体类
 *
 * @author makejava
 * @since 2021-12-26 11:01:13
 */
public class User implements Serializable {
    private static final long serialVersionUID = -49022413377249925L;
    /**
     * id
     */
    private Integer uId;


    /**
     * 昵称
     */
    private String uNick;
    /**
     * 昵称
     */
    private String uRole;

    /**
     * 账号
     */
    private String uAccount;
    /**
     * 密码
     */
    private String uPassword;
    /**
     * 邮箱
     */
    private String uEmail;
    /**
     * 手机
     */
    private String uPhone;
    /**
     * 头像
     */
    private String uHeadImg;
    /**
     * 性别
     */
    private String uGender;

    public String getuRole() {
        return uRole;
    }

    public void setuRole(String uRole) {
        this.uRole = uRole;
    }

    public Integer getUId() {
        return uId;
    }

    public void setUId(Integer uId) {
        this.uId = uId;
    }

    public String getUNick() {
        return uNick;
    }

    public void setUNick(String uNick) {
        this.uNick = uNick;
    }

    public String getUAccount() {
        return uAccount;
    }

    public void setUAccount(String uAccount) {
        this.uAccount = uAccount;
    }

    public String getUPassword() {
        return uPassword;
    }

    public void setUPassword(String uPassword) {
        this.uPassword = uPassword;
    }

    public String getUEmail() {
        return uEmail;
    }

    public void setUEmail(String uEmail) {
        this.uEmail = uEmail;
    }

    public String getUPhone() {
        return uPhone;
    }

    public void setUPhone(String uPhone) {
        this.uPhone = uPhone;
    }

    public String getUHeadImg() {
        return uHeadImg;
    }

    public void setUHeadImg(String uHeadImg) {
        this.uHeadImg = uHeadImg;
    }

    public String getUGender() {
        return uGender;
    }

    public void setUGender(String uGender) {
        this.uGender = uGender;
    }

    @Override
    public String toString() {
        return "User{" +
                "uId=" + uId +
                ", uNick='" + uNick + '\'' +
                ", uRole='" + uRole + '\'' +
                ", uAccount='" + uAccount + '\'' +
                ", uPassword='" + uPassword + '\'' +
                ", uEmail='" + uEmail + '\'' +
                ", uPhone='" + uPhone + '\'' +
                ", uHeadImg='" + uHeadImg + '\'' +
                ", uGender='" + uGender + '\'' +
                '}';
    }
}

