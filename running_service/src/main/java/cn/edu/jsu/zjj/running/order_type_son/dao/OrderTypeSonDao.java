package cn.edu.jsu.zjj.running.order_type_son.dao;

import cn.edu.jsu.zjj.running.order_type_son.entity.OrderTypeSon;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * 订单子类型表(OrderTypeSon)表数据库访问层
 *
 * @author makejava
 * @since 2021-12-26 10:59:55
 */
public interface OrderTypeSonDao {

    /**
     * 通过类型名查询数据
     *

     * @return 实例对象
     */
    Integer select(Integer otId);

    /**
     * 通过类型名查询数据
     *

     * @return 实例对象
     */
    Integer select1(String tsName);

    /**
     * 通过ID查询单条数据
     *
     * @param tsId 主键
     * @return 实例对象
     */
    OrderTypeSon queryById(Integer tsId);

    /**
     * 查询指定行数据
     *
     * @param orderTypeSon 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<OrderTypeSon> queryAllByLimit(OrderTypeSon orderTypeSon, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param orderTypeSon 查询条件
     * @return 总行数
     */
    long count(OrderTypeSon orderTypeSon);

    /**
     * 新增数据
     *
     * @param orderTypeSon 实例对象
     * @return 影响行数
     */
    int insert(OrderTypeSon orderTypeSon);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<OrderTypeSon> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<OrderTypeSon> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<OrderTypeSon> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<OrderTypeSon> entities);

    /**
     * 修改数据
     *
     * @param orderTypeSon 实例对象
     * @return 影响行数
     */
    int update(OrderTypeSon orderTypeSon);

    /**
     * 通过主键删除数据
     *
     * @param tsId 主键
     * @return 影响行数
     */
    int deleteById(Integer tsId);

    List<OrderTypeSon> queryByOtId(Integer otId);

}

