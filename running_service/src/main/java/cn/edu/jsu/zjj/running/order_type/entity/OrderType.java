package cn.edu.jsu.zjj.running.order_type.entity;

import java.io.Serializable;

/**
 * 订单类型表(OrderType)实体类
 *
 * @author makejava
 * @since 2021-12-26 10:58:01
 */
public class OrderType implements Serializable {
    private static final long serialVersionUID = 925746850798456635L;
    /**
     * ID
     */
    private Integer otId;
    /**
     * 类型名
     */
    private String otName;


    public Integer getOtId() {
        return otId;
    }

    public void setOtId(Integer otId) {
        this.otId = otId;
    }

    public String getOtName() {
        return otName;
    }

    public void setOtName(String otName) {
        this.otName = otName;
    }

}

