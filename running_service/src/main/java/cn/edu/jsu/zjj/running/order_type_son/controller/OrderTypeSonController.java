package cn.edu.jsu.zjj.running.order_type_son.controller;

import cn.edu.jsu.zjj.running.order_type_son.entity.OrderTypeSon;
import cn.edu.jsu.zjj.running.order_type_son.service.OrderTypeSonService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 订单子类型表(OrderTypeSon)表控制层
 *
 * @author makejava
 * @since 2021-12-26 10:59:55
 */
@RestController
@RequestMapping("orderTypeSon")
public class OrderTypeSonController {
    /**
     * 服务对象
     */
    @Resource
    private OrderTypeSonService orderTypeSonService;

    /**
     * 分页查询
     *
     * @param orderTypeSon 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<OrderTypeSon>> queryByPage(OrderTypeSon orderTypeSon, PageRequest pageRequest) {
        return ResponseEntity.ok(this.orderTypeSonService.queryByPage(orderTypeSon, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<OrderTypeSon> queryById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.orderTypeSonService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param orderTypeSon 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<OrderTypeSon> add(OrderTypeSon orderTypeSon) {
        return ResponseEntity.ok(this.orderTypeSonService.insert(orderTypeSon));
    }

    /**
     * 编辑数据
     *
     * @param orderTypeSon 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<OrderTypeSon> edit(OrderTypeSon orderTypeSon) {
        return ResponseEntity.ok(this.orderTypeSonService.update(orderTypeSon));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Integer id) {
        return ResponseEntity.ok(this.orderTypeSonService.deleteById(id));
    }

}

