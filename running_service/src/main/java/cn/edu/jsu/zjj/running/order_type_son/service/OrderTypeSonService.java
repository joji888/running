package cn.edu.jsu.zjj.running.order_type_son.service;

import cn.edu.jsu.zjj.running.order_type_son.entity.OrderTypeSon;
import cn.edu.jsu.zjj.running.utils.Result;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * 订单子类型表(OrderTypeSon)表服务接口
 *
 * @author makejava
 * @since 2021-12-26 10:59:55
 */
public interface OrderTypeSonService {

    /**
     * 通过ID查询单条数据
     *
     * @param tsId 主键
     * @return 实例对象
     */
    Result queryById(Integer tsId);

    /**
     * 分页查询
     *
     * @param orderTypeSon 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<OrderTypeSon> queryByPage(OrderTypeSon orderTypeSon, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param orderTypeSon 实例对象
     * @return 实例对象
     */
    Result insert(OrderTypeSon orderTypeSon);

    /**
     * 修改数据
     *
     * @param orderTypeSon 实例对象
     * @return 实例对象
     */
    Result update(OrderTypeSon orderTypeSon);

    /**
     * 通过主键删除数据
     *
     * @param tsId 主键
     * @return 是否成功
     */
    Result deleteById(Integer tsId);

}
