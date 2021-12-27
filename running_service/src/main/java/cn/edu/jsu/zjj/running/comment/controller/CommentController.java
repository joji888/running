package cn.edu.jsu.zjj.running.comment.controller;

import cn.edu.jsu.zjj.running.comment.entity.Comment;
import cn.edu.jsu.zjj.running.comment.service.CommentService;
import cn.edu.jsu.zjj.running.utils.Result;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 评论表(Comment)表控制层
 *
 * @author makejava
 * @since 2021-12-26 10:52:11
 */
@RestController
@RequestMapping("comment")
public class CommentController {
    /**
     * 服务对象
     */
    @Resource
    private CommentService commentService;

    /**
     * 分页查询
     *
     * @param comment 筛选条件
     * @return 查询结果
     */
    @GetMapping
    public Result<ResponseEntity<Page<Comment>>> queryByPage(Comment comment, Integer size, Integer page) {
        PageRequest pageRequest = PageRequest.of(page,size);
        Page<Comment> comments = this.commentService.queryByPage(comment,pageRequest);
        return Result.success(ResponseEntity.ok(comments));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public Result queryById(@PathVariable("id") Integer id) {
        return this.commentService.queryById(id);
    }

    /**
     * 新增数据
     *
     * @param comment 实体
     * @return 新增结果
     */
    @PostMapping
    public Result add(Comment comment) {
        return this.commentService.insert(comment);
    }

    /**
     * 编辑数据
     *
     * @param comment 实体
     * @return 编辑结果
     */
    @PutMapping
    public Result edit(Comment comment) {
        return this.commentService.update(comment);
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public Result deleteById(Integer id) {
        return this.commentService.deleteById(id);
    }

}

