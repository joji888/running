package cn.edu.jsu.zjj.running.order.controller;

import cn.edu.jsu.zjj.running.order.entity.Order;
import cn.edu.jsu.zjj.running.order.service.OrderService;
import cn.edu.jsu.zjj.running.utils.Result;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 订单表(Order)表控制层
 *
 * @author makejava
 * @since 2021-12-26 10:52:50
 */
@RestController
@RequestMapping("order")
public class OrderController {
    /**
     * 服务对象
     */
    @Resource
    private OrderService orderService;

    /**
     * 分页查询
     *
     * @param order 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<Order>> queryByPage(Order order, PageRequest pageRequest) {
        return ResponseEntity.ok(this.orderService.queryByPage(order, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public Result queryById(@PathVariable("id") Integer id) {
        return this.orderService.queryById(id);
    }

    /**
     * 新增数据
     *
     * @param order 实体
     * @return 新增结果
     */
    @PostMapping
    public Result add(Order order) {
        return this.orderService.insert(order);
    }

    /**
     * 编辑数据
     *
     * @param order 实体
     * @return 编辑结果
     */
    @PutMapping
    public Result edit(Order order) {
        return this.orderService.update(order);
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public Result deleteById(Integer id) {
        return this.orderService.deleteById(id);
    }

}
