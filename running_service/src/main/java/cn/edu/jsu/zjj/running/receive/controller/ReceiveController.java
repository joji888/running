package cn.edu.jsu.zjj.running.receive.controller;

import cn.edu.jsu.zjj.running.receive.entity.Receive;
import cn.edu.jsu.zjj.running.receive.service.ReceiveService;
import cn.edu.jsu.zjj.running.utils.Result;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 跑腿接单表(Receive)表控制层
 *
 * @author makejava
 * @since 2021-12-26 11:00:23
 */
@RestController
@RequestMapping("receive")
public class ReceiveController {
    /**
     * 服务对象
     */
    @Resource
    private ReceiveService receiveService;

    /**
     * 分页查询
     *
     * @param receive 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<Receive>> queryByPage(Receive receive, PageRequest pageRequest) {
        return ResponseEntity.ok(this.receiveService.queryByPage(receive, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public Result<Receive> queryById(@PathVariable("id") Integer id) {
        return this.receiveService.queryById(id);
    }

    /**
     * 新增数据
     *
     * @param receive 实体
     * @return 新增结果
     */
    @PostMapping
    public Result add(Receive receive) {
        return this.receiveService.insert(receive);
    }

    /**
     * 编辑数据
     *
     * @param receive 实体
     * @return 编辑结果
     */
    @PutMapping
    public Result edit(Receive receive) {
        return this.receiveService.update(receive);
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public Result deleteById(Integer id) {
        return this.receiveService.deleteById(id);
    }

}

