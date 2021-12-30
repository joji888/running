package cn.edu.jsu.zjj.running.order.entity;
import cn.edu.jsu.zjj.running.user.entity.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import cn.edu.jsu.zjj.running.order_type.entity.OrderType;
import cn.edu.jsu.zjj.running.order_type_son.entity.OrderTypeSon;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import cn.edu.jsu.zjj.running.order_type.entity.OrderType;
import cn.edu.jsu.zjj.running.order_type_son.entity.OrderTypeSon;

import java.util.Date;
import java.io.Serializable;

/**
 * 订单表(Order)实体类
 *
 * @author makejava
 * @since 2021-12-26 10:52:50
 */
public class Order implements Serializable {
    private static final long serialVersionUID = 804747190808598637L;
    /**
     * ID
     */
    private Integer oId;
    /**
     * 用户ID
     */
    private Integer uId;
    /**
     * 订单子类
     */
    private Integer tsId;
    /**
     * 订单类型
     */
    private Integer otId;
    /**
     * 标题
     */
    private String oTile;
    /**
     * 价格
     */
    private Integer oPrice;
    /**
     * 订单状态(0过期，1正常，2已接单，3结束)
     */
    private Integer oState;
    /**
     * 订单图片
     */
    private String oImage;
    /**
     * 订单描述
     */
    private String oDescribe;
    /**
     * 订单结束时间
     */
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm",timezone="GMT+8")
    private Date oEndTime;
    /**
     * 订单发布时间
     */
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm",timezone="GMT+8")
    private Date oCreateTime;

    private OrderType orderType;

    private OrderTypeSon orderTypeSon;

    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public OrderType getOrderType() {
        return orderType;
    }

    public void setOrderType(OrderType orderType) {
        this.orderType = orderType;
    }

    public OrderTypeSon getOrderTypeSon() {
        return orderTypeSon;
    }

    public void setOrderTypeSon(OrderTypeSon orderTypeSon) {
        this.orderTypeSon = orderTypeSon;
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

    public Integer getTsId() {
        return tsId;
    }

    public void setTsId(Integer tsId) {
        this.tsId = tsId;
    }

    public Integer getOtId() {
        return otId;
    }

    public void setOtId(Integer otId) {
        this.otId = otId;
    }

    public String getOTile() {
        return oTile;
    }

    public void setOTile(String oTile) {
        this.oTile = oTile;
    }

    public Integer getoPrice() {
        return oPrice;
    }

    public void setoPrice(Integer oPrice) {
        this.oPrice = oPrice;
    }

    public Integer getOState() {
        return oState;
    }

    public void setOState(Integer oState) {
        this.oState = oState;
    }

    public String getOImage() {
        return oImage;
    }

    public void setOImage(String oImage) {
        this.oImage = oImage;
    }

    public String getODescribe() {
        return oDescribe;
    }

    public void setODescribe(String oDescribe) {
        this.oDescribe = oDescribe;
    }

    public Date getOEndTime() {
        return oEndTime;
    }

    public void setOEndTime(Date oEndTime) {
        this.oEndTime = oEndTime;
    }

    public Date getOCreateTime() {
        return oCreateTime;
    }

    public void setOCreateTime(Date oCreateTime) {
        this.oCreateTime = oCreateTime;
    }

}

