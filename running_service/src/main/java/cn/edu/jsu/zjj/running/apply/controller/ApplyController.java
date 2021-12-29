package cn.edu.jsu.zjj.running.apply.controller;

import cn.edu.jsu.zjj.running.apply.entity.Apply;
import cn.edu.jsu.zjj.running.apply.service.ApplyService;
import cn.edu.jsu.zjj.running.utils.Result;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 用户申请跑腿表(Apply)表控制层
 *
 * @author PL
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
     * @param apply 筛选条件
     * @return 查询结果
     */
    @GetMapping
    public Result<ResponseEntity<Page<Apply>>> queryByPage(Apply apply, Integer size, Integer page) {
        PageRequest pageRequest = PageRequest.of(page,size);
        Page<Apply> applies = this.applyService.queryByPage(apply,pageRequest);
        return Result.success(ResponseEntity.ok(applies));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public Result queryById(@PathVariable("id") Integer id) {
        return this.applyService.queryById(id);
    }

    /**
     * 新增数据
     *
     * @param apply 实体
     * @return 新增结果
     */
    @PostMapping
    public Result add(Apply apply) {
        return this.applyService.insert(apply);
    }

    /**
     * 编辑数据
     *
     * @param apply 实体
     * @return 编辑结果
     */
    @PutMapping
    public Result edit(Apply apply) {
        return this.applyService.update(apply);
    }


    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public Result deleteById(Integer id) {
        return this.applyService.deleteById(id);
    }

}

