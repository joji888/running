package cn.edu.jsu.zjj.running.comment.service.impl;

import cn.edu.jsu.zjj.running.comment.entity.Comment;
import cn.edu.jsu.zjj.running.comment.dao.CommentDao;
import cn.edu.jsu.zjj.running.comment.service.CommentService;
import cn.edu.jsu.zjj.running.utils.Result;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 * 评论表(Comment)表服务实现类
 *
 * @author PL
 * @since 2021-12-26 10:52:11
 */
@Service("commentService")
public class CommentServiceImpl implements CommentService {
    @Resource
    private CommentDao commentDao;

    /**
     * 通过ID查询单条数据
     *
     * @param cId 主键
     * @return 实例对象
     */
    @Override
    public Result queryById(Integer cId) {
        if (cId==null || cId<1){
            return Result.error("用户申请Id不能为空或等于0");
        }
        return Result.success(this.commentDao.queryById(cId));
    }

    /**
     * 分页查询
     *
     * @param comment 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<Comment> queryByPage(Comment comment, PageRequest pageRequest) {
        long total = this.commentDao.count(comment);
        return new PageImpl<>(this.commentDao.queryAllByLimit(comment, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param comment 实例对象
     * @return 实例对象
     */
    @Override
    public Result insert(Comment comment) {
        if (comment.getOId()==null || comment.getOId().equals("")){
            return Result.error("订单ID不能为空");
        }
        if (comment.getUId()==null || comment.getUId().equals("")){
            return Result.error("用户ID不能为空");
        }
        if (comment.getCTime()==null || comment.getCTime().equals("")){
            return Result.error("评论时间不能为空");
        }
        if (comment.getCType()==null || comment.getCType().equals("")){
            return Result.error("评论类型不能为空");
        }

        Integer insert = commentDao.insert(comment);
        if (insert>0){
            return Result.success("添加成功");
        }else{
            return Result.error("添加失败");
        }
    }

    /**
     * 修改数据
     *
     * @param comment 实例对象
     * @return 实例对象
     */
    @Override
    public Result update(Comment comment) {
        if (comment.getCId()==null || comment.getCId()<1){
            return Result.error("评论ID不能为空");
        }
//        if (comment.getOId()==null || comment.getOId().equals("")){
//            return Result.error("订单ID不能为空");
//        }
//        if (comment.getUId()==null || comment.getUId().equals("")){
//            return Result.error("用户ID不能为空");
//        }
//        if (comment.getCTime()==null || comment.getCTime().equals("")){
//            return Result.error("评论时间不能为空");
//        }
        if (comment.getCType()==null || comment.getCType().equals("")){
            return Result.error("评论类型不能为空");
        }
        if (comment.getCContent()==null || comment.getCContent().equals("")){
            return Result.error("评论内容不能为空");
        }
        Integer update = this.commentDao.update(comment);
        if (update>0){
            return Result.success("修改成功");
        }
        return Result.error("修改失败");
    }

    /**
     * 通过主键删除数据
     *
     * @param cId 主键
     * @return 是否成功
     */
    @Override
    public Result deleteById(Integer cId) {
        if (cId==null || cId<1){
            return Result.error("评论ID不能为空或小于0");
        }
        Integer del = this.commentDao.deleteById(cId);
        if (del>0){
            return Result.success("删除成功");
        }
        return Result.error("删除失败");
    }
}
