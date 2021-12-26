package cn.edu.jsu.zjj.running.receive.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * 跑腿接单表(Receive)实体类
 *
 * @author makejava
 * @since 2021-12-26 11:00:24
 */
public class Receive implements Serializable {
    private static final long serialVersionUID = -72123102265667971L;
    /**
     * ID
     */
    private Integer rId;
    /**
     * 开始时间
     */
    private Date rBeginTime;
    /**
     * 结束时间
     */
    private Date rEndTime;
    /**
     * 跑腿状态（0结束，1开始）
     */
    private Integer rSeate;


    public Integer getRId() {
        return rId;
    }

    public void setRId(Integer rId) {
        this.rId = rId;
    }

    public Date getRBeginTime() {
        return rBeginTime;
    }

    public void setRBeginTime(Date rBeginTime) {
        this.rBeginTime = rBeginTime;
    }

    public Date getREndTime() {
        return rEndTime;
    }

    public void setREndTime(Date rEndTime) {
        this.rEndTime = rEndTime;
    }

    public Integer getRSeate() {
        return rSeate;
    }

    public void setRSeate(Integer rSeate) {
        this.rSeate = rSeate;
    }

}

