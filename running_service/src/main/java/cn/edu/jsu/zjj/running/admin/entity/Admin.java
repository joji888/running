package cn.edu.jsu.zjj.running.admin.entity;

import java.io.Serializable;

/**
 * 管理员表(Admin)实体类
 *
 * @author PL
 * @since 2021-12-26 10:49:28
 */
public class Admin implements Serializable {
    private static final long serialVersionUID = 334124229853823683L;
    /**
     * ID
     */
    private Integer aId;
    /**
     * 角色
     */
    private String aRole;
    /**
     * 账号
     */
    private String aAccount;
    /**
     * 密码
     */
    private String aPassword;


    public Integer getAId() {
        return aId;
    }

    public void setAId(Integer aId) {
        this.aId = aId;
    }

    public String getaRole() {
        return aRole;
    }

    public void setaRole(String aRole) {
        this.aRole = aRole;
    }

    public String getAAccount() {
        return aAccount;
    }

    public void setAAccount(String aAccount) {
        this.aAccount = aAccount;
    }

    public String getAPassword() {
        return aPassword;
    }

    public void setAPassword(String aPassword) {
        this.aPassword = aPassword;
    }

}

