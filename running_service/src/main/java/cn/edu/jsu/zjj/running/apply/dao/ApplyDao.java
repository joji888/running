package cn.edu.jsu.zjj.running.apply.dao;

import cn.edu.jsu.zjj.running.apply.entity.Apply;
import cn.edu.jsu.zjj.running.user.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户申请跑腿表(Apply)表数据库访问层
 *
 * @author makejava
 * @since 2021-12-26 10:50:46
 */
@Repository
public interface ApplyDao {

    /**
     * 通过ID查询单条数据
     *
     * @param applyId 主键
     * @return 实例对象
     */
    Apply queryById(Integer applyId);

    /**
     * 查询指定行数据
     *
     * @param apply 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<Apply> queryAllByLimit(Apply apply, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param apply 查询条件
     * @return 总行数
     */
    long count(Apply apply);

    /**
     * 新增数据
     *
     * @param apply 实例对象
     * @return 影响行数
     */
    int insert(Apply apply);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Apply> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Apply> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Apply> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<Apply> entities);

    /**
     * 修改数据
     *
     * @param apply 实例对象
     * @return 影响行数
     */
    int update(Apply apply);

    /**
     * 通过主键删除数据
     *
     * @param applyId 主键
     * @return 影响行数
     */
    int deleteById(Integer applyId);
}

