package cn.edu.jsu.zjj.running.order.controller;

import cn.edu.jsu.zjj.running.order.entity.Order;
import cn.edu.jsu.zjj.running.order.service.OrderService;
import cn.edu.jsu.zjj.running.utils.Encryption;
import cn.edu.jsu.zjj.running.utils.MailSend;
import cn.edu.jsu.zjj.running.utils.RandomUtil;
import cn.edu.jsu.zjj.running.utils.Result;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * 订单表(Order)表控制层
 *
 * @author PL
 * @since 2021-12-26 10:52:50
 */
@RestController
@RequestMapping("order")
public class OrderController {
    /**
     * 服务对象
     */
    private OrderService orderService;

    private MailSend mailSend;

    public OrderController(OrderService orderService, MailSend mailSend) {
        this.orderService = orderService;
        this.mailSend = mailSend;
    }

    /**
     * 分页查询
     *
     * @param order 筛选条件
     * @return 查询结果
     */
    @GetMapping
    public Result<ResponseEntity<Page<Order>>> queryByPage(Order order, Integer size, Integer page) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Order> orders = this.orderService.queryByPage(order, pageRequest);
        return Result.success(ResponseEntity.ok(orders));
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

    @GetMapping("queryByState")
    public Result queryByState(Integer oState){
        return this.orderService.queryByState(oState);
    }
    /**
     * 新增数据
     *
     * @param order 实体
     * @return 新增结果
     */
    @PostMapping
    public Result add(MultipartFile file,Order order) throws IOException {
        return this.orderService.insert(file,order);
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
     * 编辑数据
     *
     * @param image 实体
     * @return 编辑结果
     */
    @PostMapping("editImg")
    public Result editImg(MultipartFile uploadFIle, Integer oId, String image) throws IOException {
        return this.orderService.editImg(uploadFIle,oId,image);
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

