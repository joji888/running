package cn.edu.jsu.zjj.running.order_type.dao;

import cn.edu.jsu.zjj.running.order_type.entity.OrderType;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 订单类型表(OrderType)表数据库访问层
 *
 * @author makejava
 * @since 2021-12-26 10:58:01
 */
@Repository
public interface OrderTypeDao {

    /**
     * 通过ID查询单条数据
     *
     * @param otId 主键
     * @return 实例对象
     */
    OrderType queryById(Integer otId);

    /**
     * 查询指定行数据
     *

     * @return 对象列表
     */
    List<OrderType> queryAllByLimit(OrderType orderType,@Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param orderType 查询条件
     * @return 总行数
     */
    long count(OrderType orderType);

    /**
     * 新增数据
     *
     * @param orderType 实例对象
     * @return 影响行数
     */
    int insert(OrderType orderType);
    Integer select(String otName);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<OrderType> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<OrderType> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<OrderType> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<OrderType> entities);

    /**
     * 修改数据
     *
     * @param orderType 实例对象
     * @return 影响行数
     */
    int update(OrderType orderType);

    /**
     * 通过主键删除数据
     *
     * @param otId 主键
     * @return 影响行数
     */
    int deleteById(Integer otId);

}

