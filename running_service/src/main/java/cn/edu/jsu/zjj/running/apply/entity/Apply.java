package cn.edu.jsu.zjj.running.apply.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * 用户申请跑腿表(Apply)实体类
 *
 * @author makejava
 * @since 2021-12-26 10:50:46
 */
public class Apply implements Serializable {
    private static final long serialVersionUID = -12304888286675452L;
    /**
     * ID
     */
    private Integer applyId;
    /**
     * 申请时间
     */
    private Date applyTime;
    /**
     * 申请状态
     */
    private String applyState;
    /**
     * 身份证
     */
    private String applyIdentityCode;
    /**
     * 身份证正面
     */
    private String applyIdentityCodeFront;
    /**
     * 身份证背面
     */
    private String applyIdentityCodeBack;


    public Integer getApplyId() {
        return applyId;
    }

    public void setApplyId(Integer applyId) {
        this.applyId = applyId;
    }

    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    public String getApplyState() {
        return applyState;
    }

    public void setApplyState(String applyState) {
        this.applyState = applyState;
    }

    public String getApplyIdentityCode() {
        return applyIdentityCode;
    }

    public void setApplyIdentityCode(String applyIdentityCode) {
        this.applyIdentityCode = applyIdentityCode;
    }

    public String getApplyIdentityCodeFront() {
        return applyIdentityCodeFront;
    }

    public void setApplyIdentityCodeFront(String applyIdentityCodeFront) {
        this.applyIdentityCodeFront = applyIdentityCodeFront;
    }

    public String getApplyIdentityCodeBack() {
        return applyIdentityCodeBack;
    }

    public void setApplyIdentityCodeBack(String applyIdentityCodeBack) {
        this.applyIdentityCodeBack = applyIdentityCodeBack;
    }

}

