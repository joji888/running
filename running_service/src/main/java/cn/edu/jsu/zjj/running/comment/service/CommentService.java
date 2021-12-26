package cn.edu.jsu.zjj.running.comment.service;

import cn.edu.jsu.zjj.running.comment.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * 评论表(Comment)表服务接口
 *
 * @author makejava
 * @since 2021-12-26 10:52:11
 */
public interface CommentService {

    /**
     * 通过ID查询单条数据
     *
     * @param cId 主键
     * @return 实例对象
     */
    Comment queryById(Integer cId);

    /**
     * 分页查询
     *
     * @param comment 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<Comment> queryByPage(Comment comment, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param comment 实例对象
     * @return 实例对象
     */
    Comment insert(Comment comment);

    /**
     * 修改数据
     *
     * @param comment 实例对象
     * @return 实例对象
     */
    Comment update(Comment comment);

    /**
     * 通过主键删除数据
     *
     * @param cId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer cId);

}
