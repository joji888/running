package cn.edu.jsu.zjj.running.order_type.service.impl;

import cn.edu.jsu.zjj.running.order_type.entity.OrderType;
import cn.edu.jsu.zjj.running.order_type.dao.OrderTypeDao;
import cn.edu.jsu.zjj.running.order_type.service.OrderTypeService;
import cn.edu.jsu.zjj.running.utils.Result;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.List;

/**
 * 订单类型表(OrderType)表服务实现类
 *
 * @author makejava
 * @since 2021-12-26 10:58:01
 */
@Service("orderTypeService")
public class OrderTypeServiceImpl implements OrderTypeService {
    @Resource
    private OrderTypeDao orderTypeDao;

    /**
     * 通过ID查询单条数据
     *
     * @param otId 主键
     * @return 实例对象
     */
    @Override
    public Result queryById(Integer otId){
        if (otId == null || otId.equals("")){
            return Result.error("ID不能为空");
        }
        return Result.success(this.orderTypeDao.queryById(otId));

    }

    /**
     * 分页查询
     *
     * @param offset 筛选条件
     * @param limit      分页对象
     * @return 查询结果
     */
    @Override
    public Result<List<OrderType>> queryByPage(Integer offset,Integer limit) {
        if (offset == null || offset < 1){
            offset = 1;
        }
        if (limit ==null){
            limit = 10;
        }
        offset = (offset-1)*limit;
        return Result.success(this.orderTypeDao.queryAllByLimit(offset,limit));
    }

    /**
     * 新增数据
     *
     * @param orderType 实例对象
     * @return 实例对象
     */
    @Override
    public Result insert(OrderType orderType) {
        if (orderType.getOtName() == null || orderType.getOtName().equals("")){
            return Result.error("类型名不能为空");
        }
        Integer insert = this.orderTypeDao.insert(orderType);
        if (insert > 0){
            return Result.error("新增数据成功");
        }else {
            return Result.error("新增数据失败");
        }
    }

    /**
     * 修改数据
     *
     * @param orderType 实例对象
     * @return 实例对象
     */
    @Override
    public Result update(OrderType orderType) {
        if (orderType.getOtId() == null || orderType.getOtId().equals("")){
            return Result.error("ID不能为空");
        }
        if (orderType.getOtName() == null || orderType.getOtName().equals("")){
            return Result.error("类型名不能够为空");
        }

        Integer update = this.orderTypeDao.update(orderType);

        if (update > 0){
            return Result.error("修改数据成功");
        }else {
            return Result.error("修改数据失败");
        }
    }

    /**
     * 通过主键删除数据
     *
     * @param otId 主键
     * @return 是否成功
     */
    @Override
    public Result deleteById(Integer otId) {
        if (otId == null || otId.equals("")){
            return Result.error("ID不能为空");
        }

        Integer deleteById = this.orderTypeDao.deleteById(otId);

        if (deleteById > 0){
            return Result.error("删除成功");
        }else{
            return Result.error("删除失败");
        }

    }
}
