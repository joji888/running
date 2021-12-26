package cn.edu.jsu.zjj.running.order_type.controller;

import cn.edu.jsu.zjj.running.order_type.entity.OrderType;
import cn.edu.jsu.zjj.running.order_type.service.OrderTypeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

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
     * @param orderType 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<OrderType>> queryByPage(OrderType orderType, PageRequest pageRequest) {
        return ResponseEntity.ok(this.orderTypeService.queryByPage(orderType, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<OrderType> queryById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.orderTypeService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param orderType 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<OrderType> add(OrderType orderType) {
        return ResponseEntity.ok(this.orderTypeService.insert(orderType));
    }

    /**
     * 编辑数据
     *
     * @param orderType 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<OrderType> edit(OrderType orderType) {
        return ResponseEntity.ok(this.orderTypeService.update(orderType));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Integer id) {
        return ResponseEntity.ok(this.orderTypeService.deleteById(id));
    }

}

