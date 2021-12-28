package cn.edu.jsu.zjj.running.order_type_son.entity;

import cn.edu.jsu.zjj.running.order_type.entity.OrderType;

import java.io.Serializable;

/**
 * 订单子类型表(OrderTypeSon)实体类
 *
 * @author makejava
 * @since 2021-12-26 10:59:55
 */
public class OrderTypeSon implements Serializable {
    private static final long serialVersionUID = -43200483766456883L;
    /**
     * ID
     */
    private Integer tsId;

    /**
     * 类型名ID
     */
    private Integer otId;

    /**
     * 子类型名
     */
    private String tsName;
    /**
     * 单价
     */
    private Integer tsPrice;

    private OrderType orderType;

    public OrderType getOrderType() {
        return orderType;
    }

    public void setOrderType(OrderType orderType) {
        this.orderType = orderType;
    }

    public Integer getOtId() {
        return otId;
    }

    public void setOtId(Integer otId) {
        this.otId = otId;
    }

    public Integer getTsId() {
        return tsId;
    }

    public void setTsId(Integer tsId) {
        this.tsId = tsId;
    }

    public String getTsName() {
        return tsName;
    }

    public void setTsName(String tsName) {
        this.tsName = tsName;
    }

    public Integer getTsPrice() {
        return tsPrice;
    }

    public void setTsPrice(Integer tsPrice) {
        this.tsPrice = tsPrice;
    }

}

