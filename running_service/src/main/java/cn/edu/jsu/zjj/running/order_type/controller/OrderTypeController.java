package cn.edu.jsu.zjj.running.order_type.controller;

import cn.edu.jsu.zjj.running.order_type.entity.OrderType;
import cn.edu.jsu.zjj.running.order_type.service.OrderTypeService;
import cn.edu.jsu.zjj.running.utils.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 订单类型表(OrderType)表控制层
 *
 * @author makejava
 * @since 2021-12-26 10:58:01
 */
@RestController
@RequestMapping("orderType")
public class OrderTypeController {
    /**
     * 服务对象
     */
    @Resource
    private OrderTypeService orderTypeService;

    /**
     * 分页查询
     *
     * @param offset 筛选条件
     * @param limit      分页对象
     * @return 查询结果
     */
    @GetMapping
    public Result<List<OrderType>> queryByPage(Integer offset,Integer limit) {
        return this.orderTypeService.queryByPage(offset,limit);
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public Result queryById(@PathVariable("id") Integer id) {
        return this.orderTypeService.queryById(id);
    }

    /**
     * 新增数据
     *
     * @param orderType 实体
     * @return 新增结果
     */
    @PostMapping
    public Result add(OrderType orderType) {
        return this.orderTypeService.insert(orderType);
    }

    /**
     * 编辑数据
     *
     * @param orderType 实体
     * @return 编辑结果
     */
    @PutMapping
    public Result edit(OrderType orderType) {
        return this.orderTypeService.update(orderType);
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public Result deleteById(Integer id) {
        return this.orderTypeService.deleteById(id);
    }

}

