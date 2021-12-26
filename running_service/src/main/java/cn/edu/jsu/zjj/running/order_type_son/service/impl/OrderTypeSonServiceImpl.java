package cn.edu.jsu.zjj.running.order_type_son.service.impl;

import cn.edu.jsu.zjj.running.order_type_son.entity.OrderTypeSon;
import cn.edu.jsu.zjj.running.order_type_son.dao.OrderTypeSonDao;
import cn.edu.jsu.zjj.running.order_type_son.service.OrderTypeSonService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 * 订单子类型表(OrderTypeSon)表服务实现类
 *
 * @author makejava
 * @since 2021-12-26 10:59:55
 */
@Service("orderTypeSonService")
public class OrderTypeSonServiceImpl implements OrderTypeSonService {
    @Resource
    private OrderTypeSonDao orderTypeSonDao;

    /**
     * 通过ID查询单条数据
     *
     * @param tsId 主键
     * @return 实例对象
     */
    @Override
    public OrderTypeSon queryById(Integer tsId) {
        return this.orderTypeSonDao.queryById(tsId);
    }

    /**
     * 分页查询
     *
     * @param orderTypeSon 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<OrderTypeSon> queryByPage(OrderTypeSon orderTypeSon, PageRequest pageRequest) {
        long total = this.orderTypeSonDao.count(orderTypeSon);
        return new PageImpl<>(this.orderTypeSonDao.queryAllByLimit(orderTypeSon, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param orderTypeSon 实例对象
     * @return 实例对象
     */
    @Override
    public OrderTypeSon insert(OrderTypeSon orderTypeSon) {
        this.orderTypeSonDao.insert(orderTypeSon);
        return orderTypeSon;
    }

    /**
     * 修改数据
     *
     * @param orderTypeSon 实例对象
     * @return 实例对象
     */
    @Override
    public OrderTypeSon update(OrderTypeSon orderTypeSon) {
        this.orderTypeSonDao.update(orderTypeSon);
        return this.queryById(orderTypeSon.getTsId());
    }

    /**
     * 通过主键删除数据
     *
     * @param tsId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer tsId) {
        return this.orderTypeSonDao.deleteById(tsId) > 0;
    }
}
