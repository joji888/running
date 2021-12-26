package cn.edu.jsu.zjj.running.apply.controller;

import cn.edu.jsu.zjj.running.apply.entity.Apply;
import cn.edu.jsu.zjj.running.apply.service.ApplyService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 用户申请跑腿表(Apply)表控制层
 *
 * @author makejava
 * @since 2021-12-26 10:50:46
 */
@RestController
@RequestMapping("apply")
public class ApplyController {
    /**
     * 服务对象
     */
    @Resource
    private ApplyService applyService;

    /**
     * 分页查询
     *
     * @param apply 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<Apply>> queryByPage(Apply apply, PageRequest pageRequest) {
        return ResponseEntity.ok(this.applyService.queryByPage(apply, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<Apply> queryById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.applyService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param apply 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<Apply> add(Apply apply) {
        return ResponseEntity.ok(this.applyService.insert(apply));
    }

    /**
     * 编辑数据
     *
     * @param apply 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<Apply> edit(Apply apply) {
        return ResponseEntity.ok(this.applyService.update(apply));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Integer id) {
        return ResponseEntity.ok(this.applyService.deleteById(id));
    }

}

