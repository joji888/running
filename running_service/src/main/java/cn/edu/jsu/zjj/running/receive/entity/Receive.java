package cn.edu.jsu.zjj.running.receive.entity;

import cn.edu.jsu.zjj.running.order.entity.Order;
import cn.edu.jsu.zjj.running.user.entity.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

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
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm",timezone="GMT+8")
    private Date rBeginTime;
    /**
     * 结束时间
     */
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm",timezone="GMT+8")
    private Date rEndTime;
    /**
     * 跑腿状态（0结束，1开始）
     */
    private Integer rSeate;
    /**
     * 用户id
     */
    private Integer uId;
    /**
     * 接单者id
     */
    private Integer ruId;

    private User user;


    private Integer oId;

    private Order order;


    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Integer getoId() {
        return oId;
    }

    public void setoId(Integer oId) {
        this.oId = oId;
    }

    private User rUser;


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getuId() {
        return uId;
    }

    public void setuId(Integer uId) {
        this.uId = uId;
    }

    public Integer getRuId() {
        return ruId;
    }

    public void setRuId(Integer ruId) {
        this.ruId = ruId;
    }

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

    public User getrUser() {
        return rUser;
    }

    public void setrUser(User rUser) {
        this.rUser = rUser;
    }
}

