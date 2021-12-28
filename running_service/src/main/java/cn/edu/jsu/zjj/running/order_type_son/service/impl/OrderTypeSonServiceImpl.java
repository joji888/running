package cn.edu.jsu.zjj.running.order_type_son.service.impl;

import cn.edu.jsu.zjj.running.order_type.entity.OrderType;
import cn.edu.jsu.zjj.running.order_type_son.entity.OrderTypeSon;
import cn.edu.jsu.zjj.running.order_type_son.dao.OrderTypeSonDao;
import cn.edu.jsu.zjj.running.order_type_son.service.OrderTypeSonService;
import cn.edu.jsu.zjj.running.utils.Result;
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
    public Result queryById(Integer tsId) {
        if (tsId <= 0){
            return Result.error("ID不能为空");
        }
        OrderTypeSon orderTypeSon = this.orderTypeSonDao.queryById(tsId);
        if (orderTypeSon == null){
            return Result.error("数据不存在");
        }
            return Result.success(orderTypeSon);
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
    public Result insert(OrderTypeSon orderTypeSon) {
        OrderType orderType = new OrderType();

        if (orderTypeSon.getOtId() == null || orderTypeSon.getOtId() <1){
            return Result.error("类型名ID不能为空");
        }

        if (orderTypeSon.getTsName() == null || orderTypeSon.getTsName().equals("")){
            return Result.error("子类名不能为空");
        }
        if (orderType.getOtId() == this.orderTypeSonDao.select(orderType.getOtId())){
            if (this.orderTypeSonDao.select1(orderTypeSon.getTsName())>0){
                return Result.error("同类型名的子类名不能重复");
            }
        }

        if (orderTypeSon.getTsPrice() == null || orderTypeSon.getTsPrice()<0){
            return Result.error("价格不能为空或为负数");
        }
        Integer insert = this.orderTypeSonDao.insert(orderTypeSon);
        if (insert > 0){
            return Result.success("新增数据成功");
        }
            return Result.error("新增数据失败");
    }

    /**
     * 修改数据
     *
     * @param orderTypeSon 实例对象
     * @return 实例对象
     */
    @Override
    public Result update(OrderTypeSon orderTypeSon) {

        if (orderTypeSon.getOtId() == null || orderTypeSon.getOtId() <1){
            return Result.error("类型名ID不能为空");
        }
        if (orderTypeSon.getTsId() == null || orderTypeSon.getTsId().equals("")){
            return Result.error("ID不能为空");
        }
        if (orderTypeSon.getTsName() == null || orderTypeSon.getTsName().equals("")){
            return Result.error("子类名不能为空");
        }
        if (orderTypeSon.getTsPrice() == null || orderTypeSon.getTsPrice()<0){
            return Result.error("价格不能为空或为负数");
        }
        Integer update = this.orderTypeSonDao.update(orderTypeSon);
        if (update > 0){
            return Result.success("修改数据成功");
        }
            return Result.error("修改数据失败");
    }

    /**
     * 通过主键删除数据
     *
     * @param tsId 主键
     * @return 是否成功
     */
    @Override
    public Result deleteById(Integer tsId) {
       if (tsId < 1){
           return Result.success("ID不存在");
       }
        Integer deleteById = this.orderTypeSonDao.deleteById(tsId);
       if (deleteById > 0){
           return Result.success("删除成功");
       }
           return Result.error("删除失败");
    }
}
