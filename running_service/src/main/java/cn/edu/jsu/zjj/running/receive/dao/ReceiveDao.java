package cn.edu.jsu.zjj.running.receive.dao;

import cn.edu.jsu.zjj.running.receive.entity.Receive;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * 跑腿接单表(Receive)表数据库访问层
 *
 * @author makejava
 * @since 2021-12-26 11:00:23
 */
public interface ReceiveDao {

    /**
     * 通过ID查询单条数据
     *
     * @param rId 主键
     * @return 实例对象
     */
    Receive queryById(Integer rId);

    /**
     * 查询指定行数据
     *
     * @param receive 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<Receive> queryAllByLimit(Receive receive, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param receive 查询条件
     * @return 总行数
     */
    long count(Receive receive);

    /**
     * 新增数据
     *
     * @param receive 实例对象
     * @return 影响行数
     */
    int insert(Receive receive);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Receive> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Receive> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Receive> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<Receive> entities);

    /**
     * 修改数据
     *
     * @param receive 实例对象
     * @return 影响行数
     */
    int update(Receive receive);

    /**
     * 通过主键删除数据
     *
     * @param rId 主键
     * @return 影响行数
     */
    int deleteById(Integer rId);

    Receive queryBySeate(Integer ruId, Integer rSeate);
}

