package cn.edu.jsu.zjj.running.order_type_son.controller;

import cn.edu.jsu.zjj.running.order_type_son.entity.OrderTypeSon;
import cn.edu.jsu.zjj.running.order_type_son.service.OrderTypeSonService;
import cn.edu.jsu.zjj.running.utils.Result;
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
     * @return 查询结果
     */
    @GetMapping
    public Result<ResponseEntity<Page<OrderTypeSon>>> queryByPage(OrderTypeSon orderTypeSon, Integer size,Integer page) {
        PageRequest pageRequest =PageRequest.of(page,size);
        Page<OrderTypeSon> orderTypeSons = this.orderTypeSonService.queryByPage(orderTypeSon, pageRequest);
        return Result.success(ResponseEntity.ok(orderTypeSons));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public Result<OrderTypeSon> queryById(@PathVariable("id") Integer id) {
        return this.orderTypeSonService.queryById(id);
    }

    /**
     * 新增数据
     *
     * @param orderTypeSon 实体
     * @return 新增结果
     */
    @PostMapping
    public Result add(OrderTypeSon orderTypeSon) {
        return this.orderTypeSonService.insert(orderTypeSon);
    }

    /**
     * 编辑数据
     *
     * @param orderTypeSon 实体
     * @return 编辑结果
     */
    @PutMapping
    public Result edit(OrderTypeSon orderTypeSon) {
        return this.orderTypeSonService.update(orderTypeSon);
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public Result deleteById(Integer id) {
        return this.orderTypeSonService.deleteById(id);
    }

}

