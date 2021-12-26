package cn.edu.jsu.zjj.running.order.service.impl;

import cn.edu.jsu.zjj.running.order.entity.Order;
import cn.edu.jsu.zjj.running.order.dao.OrderDao;
import cn.edu.jsu.zjj.running.order.service.OrderService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 * 订单表(Order)表服务实现类
 *
 * @author makejava
 * @since 2021-12-26 10:52:51
 */
@Service("orderService")
public class OrderServiceImpl implements OrderService {
    @Resource
    private OrderDao orderDao;

    /**
     * 通过ID查询单条数据
     *
     * @param oId 主键
     * @return 实例对象
     */
    @Override
    public Order queryById(Integer oId) {
        return this.orderDao.queryById(oId);
    }

    /**
     * 分页查询
     *
     * @param order 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<Order> queryByPage(Order order, PageRequest pageRequest) {
        long total = this.orderDao.count(order);
        return new PageImpl<>(this.orderDao.queryAllByLimit(order, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param order 实例对象
     * @return 实例对象
     */
    @Override
    public Order insert(Order order) {
        this.orderDao.insert(order);
        return order;
    }

    /**
     * 修改数据
     *
     * @param order 实例对象
     * @return 实例对象
     */
    @Override
    public Order update(Order order) {
        this.orderDao.update(order);
        return this.queryById(order.getOId());
    }

    /**
     * 通过主键删除数据
     *
     * @param oId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer oId) {
        return this.orderDao.deleteById(oId) > 0;
    }
}
