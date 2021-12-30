package cn.edu.jsu.zjj.running.receive.service.impl;

import cn.edu.jsu.zjj.running.order.dao.OrderDao;
import cn.edu.jsu.zjj.running.order.entity.Order;
import cn.edu.jsu.zjj.running.receive.entity.Receive;
import cn.edu.jsu.zjj.running.receive.dao.ReceiveDao;
import cn.edu.jsu.zjj.running.receive.service.ReceiveService;
import cn.edu.jsu.zjj.running.utils.Result;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.Date;

/**
 * 跑腿接单表(Receive)表服务实现类
 *
 * @author makejava
 * @since 2021-12-26 11:00:24
 */
@Service("receiveService")
public class ReceiveServiceImpl implements ReceiveService {
    @Resource
    private ReceiveDao receiveDao;
    @Resource
    private OrderDao orderDao;

    /**
     * 通过ID查询单条数据
     *
     * @param rId 主键
     * @return 实例对象
     */
    @Override
    public Result queryById(Integer rId) {
        if (rId <1){
            return Result.error("ID不存在");
        }
        Receive queryById = this.receiveDao.queryById(rId);
        if (queryById == null){
            return Result.error("数据不存在");
        }
            return Result.success(queryById);
    }

    /**
     * 分页查询
     *
     * @param receive 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<Receive> queryByPage(Receive receive, PageRequest pageRequest) {
        long total = this.receiveDao.count(receive);
        return new PageImpl<>(this.receiveDao.queryAllByLimit(receive, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param receive 实例对象
     * @return 实例对象
     */
    @Override
    public Result insert(Receive receive) {
        if (receive.getoId() == null || receive.getoId()<1){
            return Result.error("订单ID不能为空");
        }
        if (receive.getuId() == null || receive.getuId()<1){
            return Result.error("下单者ID不能为空");
        }
        if (receive.getRuId() == null || receive.getRuId()<1){
            return Result.error("接单者ID不能为空");
        }

        if (receive.getRBeginTime() ==null || receive.getRBeginTime().equals("")){
            return Result.error("开始时间不能为空");
        }
        if (receive.getREndTime() == null || receive.getREndTime().equals("")){
            return Result.error("结束时间不能空");
        }
        if (receive.getRSeate() !=0 && receive.getRSeate()!=1 && receive.getRSeate()!=2 && receive.getRSeate()!=3){
            return Result.error("状态错误");
        }
        if (receive.getRBeginTime().before(new Date(System.currentTimeMillis()))) {
            return Result.error("开始时间不能早于当前时间");
        }
        if (receive.getRBeginTime().after(receive.getREndTime())){
            return Result.error("结束时间不能早于开始时间");
        }

        Integer insert = this.receiveDao.insert(receive);
        if (insert > 0){
            return Result.success("新增数据成功");
        }
        return Result.error("新增数据失败");
    }

    /**
     * 修改数据
     *
     * @param receive 实例对象
     * @return 实例对象
     */
    @Override
    public Result update(Receive receive) {

        if (receive.getRId() == null || receive.getRId().equals("")){
            return Result.error("ID不能空");
        }
        if (receive.getoId() == null || receive.getoId()<1){
            return Result.error("订单ID不能为空");
        }
        if (receive.getuId() == null || receive.getuId()<1){
            return Result.error("下单者ID不能为空");
        }
        if (receive.getRuId() == null || receive.getRuId()<1){
            return Result.error("接单者ID不能为空");
        }
        if (receive.getRBeginTime() ==null || receive.getRBeginTime().equals("")){
            return Result.error("开始时间不能为空");
        }
        if (receive.getREndTime() == null || receive.getREndTime().equals("")){
            return Result.error("结束时间不能空");
        }
        if (receive.getRSeate() !=0 && receive.getRSeate()!=1 && receive.getRSeate()!=2 && receive.getRSeate()!=3){
            return Result.error("状态错误");
        }
//        if (receive.getRBeginTime().before(new Date(System.currentTimeMillis())))
//        {
//            return Result.error("开始时间不能早于当前时间");
//        }
        if (receive.getRBeginTime().after(receive.getREndTime())){
            return Result.error("结束时间不能早于开始时间");
        }

        Integer update = this.receiveDao.update(receive);
        if (update > 0){
            return Result.success("修改数据成功");
        }
            return Result.error("修改数据失败");
    }

    /**
     * 通过主键删除数据
     *
     * @param rId 主键
     * @return 是否成功
     */
    @Override
    public Result deleteById(Integer rId) {
        if (rId <1){
            return Result.error("ID不存在");
        }
        Integer deleteById= this.receiveDao.deleteById(rId);
        if (deleteById > 0){
            return Result.success("删除成功");
        }
            return Result.error("删除失败");
    }


    @Override
    public Result addById(Receive receive) {
        if (receive.getoId() ==null || receive.getoId() <1){
            return Result.error("订单ID不存在");
        }
        if (receive.getuId() ==null || receive.getuId() <1){
            return Result.error("用户ID不存在");
        }
        if (receive.getRuId() ==null || receive.getRuId() <1){
            return Result.error("跑腿者ID不存在");
        }
        receive.setRSeate(4);
        receive.setRBeginTime(new Date(System.currentTimeMillis()));
        receive.setREndTime(new Date(System.currentTimeMillis()+1000*60*10));
        Order order = this.orderDao.queryById(receive.getoId());
        order.setOState(2);
        Integer update = this.orderDao.update(order);
        Integer insert = this.receiveDao.insert(receive);
        if (insert > 0){
            if (update > 0){
            return Result.success("接单成功");
        }}
            return Result.error("接单失败");
    }

    @Override
    public Result<Receive> queryBySeate(Integer ruId, Integer rSeate) {
        if (ruId ==null || ruId <1){
            return Result.error("跑腿者ID不存在");
        }
        if (rSeate !=2){
            return Result.error("状态错误");
        }
        Receive queryBySeate =this.receiveDao.queryBySeate(ruId,rSeate);
        if (queryBySeate == null){
            return Result.error("查询失败");
        }
            return Result.success(queryBySeate);
    }


}
