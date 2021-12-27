package cn.edu.jsu.zjj.running.order_type.service;

import cn.edu.jsu.zjj.running.order_type.entity.OrderType;
import cn.edu.jsu.zjj.running.utils.Result;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;


import java.util.List;

/**
 * 订单类型表(OrderType)表服务接口
 *
 * @author makejava
 * @since 2021-12-26 10:58:01
 */
public interface OrderTypeService {

    /**
     * 通过ID查询单条数据
     *
     * @param otId 主键
     * @return 实例对象
     */
    Result queryById(Integer otId);

    /**
     * 分页查询
     *

     * @return 查询结果
     */
    Page<OrderType> queryByPage(OrderType orderType , PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param orderType 实例对象
     * @return 实例对象
     */
    Result insert(OrderType orderType);

    /**
     * 修改数据
     *
     * @param orderType 实例对象
     * @return 实例对象
     */
    Result update(OrderType orderType);

    /**
     * 通过主键删除数据
     *
     * @param otId 主键
     * @return 是否成功
     */
    Result deleteById(Integer otId);

}
