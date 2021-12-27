package cn.edu.jsu.zjj.running.order.service.impl;

import cn.edu.jsu.zjj.running.order.entity.Order;
import cn.edu.jsu.zjj.running.order.dao.OrderDao;
import cn.edu.jsu.zjj.running.order.service.OrderService;
import cn.edu.jsu.zjj.running.utils.Result;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.Date;

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
    public Result queryById(Integer oId) {
        if (oId==null || oId<1){
            return Result.error("订单Id不能为空或等于0");
        }
        return Result.success(this.orderDao.queryById(oId));
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
    public Result insert(Order order) {
        if (order.getUId()==null || order.getUId().equals("")){
            return Result.error("用户ID不能为空");
        }
        if (order.getTsId()==null || order.getTsId().equals("")){
            return Result.error("订单子类不能为空");
        }
        if (order.getOtId()==null || order.getOtId().equals("")){
            return Result.error("订单类型不能为空");
        }
        if (order.getOTile()==null || order.getOTile().equals("")){
            return Result.error("订单标题不能为空");
        }
        if (order.getOState()==null || order.getOState().equals("")){
            return Result.error("订单状态不能为空");
        }
        if (order.getOImage()==null || order.getOImage().equals("")){
            return Result.error("订单图片不能为空");
        }
        if (order.getOCreateTime()==null || order.getOCreateTime().equals("")){
            return Result.error("订单发布时间不能为空");
        }
        if (order.getOEndTime()==null || order.getOEndTime().equals("")){
            return Result.error("订单结束时间不能为空");
        }
        if (order.getOCreateTime().before(new Date(System.currentTimeMillis()))){
            return Result.error("发布时间不能早于当前时间");
        }
        if (order.getOCreateTime().after(order.getOEndTime())){
            return Result.error("订单结束时间不能早于发布时间");
        }

        Integer insert = orderDao.insert(order);
        if (insert>0){
            return Result.success("添加成功");
        }else{
            return Result.error("添加失败");
        }
    }

    /**
     * 修改数据
     *
     * @param order 实例对象
     * @return 实例对象
     */
    @Override
    public Result update(Order order) {
        if (order.getOId()==null || order.getOId().equals("")){
            return Result.error("订单ID不能为空");
        }
        if (order.getUId()==null || order.getUId().equals("")){
            return Result.error("用户ID不能为空");
        }
        if (order.getTsId()==null || order.getTsId().equals("")){
            return Result.error("订单子类不能为空");
        }
        if (order.getOtId()==null || order.getOtId().equals("")){
            return Result.error("订单类型不能为空");
        }
        if (order.getOTile()==null || order.getOTile().equals("")){
            return Result.error("订单标题不能为空");
        }
        if (order.getOState()==null || order.getOState().equals("")){
            return Result.error("订单状态不能为空");
        }
        if (order.getOImage()==null || order.getOImage().equals("")){
            return Result.error("订单图片不能为空");
        }

        if (order.getOCreateTime()==null || order.getOCreateTime().equals("")){
            return Result.error("订单发布时间不能为空");
        }
        if (order.getOEndTime()==null || order.getOEndTime().equals("")){
            return Result.error("订单结束时间不能为空");
        }
        if (order.getOCreateTime().after(order.getOEndTime())){
            return Result.error("订单结束时间不能早于发布时间");
        }
        Integer update = this.orderDao.update(order);
        if (update>0){
            return Result.success("修改成功");
        }
        return Result.error("修改失败");
    }

    /**
     * 通过主键删除数据
     *
     * @param oId 主键
     * @return 是否成功
     */
    @Override
    public Result deleteById(Integer oId) {
        if (oId==null || oId<1){
            return Result.error("订单ID不能为空或小于0");
        }
        Integer del = this.orderDao.deleteById(oId);
        if (del>0){
            return Result.success("删除成功");
        }
        return Result.error("删除失败");
    }
}
