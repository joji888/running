package cn.edu.jsu.zjj.running.comment.controller;

import cn.edu.jsu.zjj.running.comment.entity.Comment;
import cn.edu.jsu.zjj.running.comment.service.CommentService;
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
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<Comment>> queryByPage(Comment comment, PageRequest pageRequest) {
        return ResponseEntity.ok(this.commentService.queryByPage(comment, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<Comment> queryById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.commentService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param comment 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<Comment> add(Comment comment) {
        return ResponseEntity.ok(this.commentService.insert(comment));
    }

    /**
     * 编辑数据
     *
     * @param comment 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<Comment> edit(Comment comment) {
        return ResponseEntity.ok(this.commentService.update(comment));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Integer id) {
        return ResponseEntity.ok(this.commentService.deleteById(id));
    }

}

