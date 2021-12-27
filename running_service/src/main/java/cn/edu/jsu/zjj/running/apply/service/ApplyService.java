package cn.edu.jsu.zjj.running.apply.service;

import cn.edu.jsu.zjj.running.apply.entity.Apply;
import cn.edu.jsu.zjj.running.utils.Result;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * 用户申请跑腿表(Apply)表服务接口
 *
 * @author PL
 * @since 2021-12-26 10:50:46
 */
public interface ApplyService {

    /**
     * 通过ID查询单条数据
     *
     * @param applyId 主键
     * @return 实例对象
     */
    Result queryById(Integer applyId);

    /**
     * 分页查询
     *
     * @param apply 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<Apply> queryByPage(Apply apply, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param apply 实例对象
     * @return 实例对象
     */
    Result insert(Apply apply);

    /**
     * 修改数据
     *
     * @param apply 实例对象
     * @return 实例对象
     */
    Result update(Apply apply);

    /**
     * 通过主键删除数据
     *
     * @param applyId 主键
     * @return 是否成功
     */
    Result deleteById(Integer applyId);

}
