package cn.edu.jsu.zjj.running.admin.dao;

import cn.edu.jsu.zjj.running.admin.entity.Admin;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * 管理员表(Admin)表数据库访问层
 *
 * @author PL
 * @since 2021-12-26 10:49:28
 */
public interface AdminDao {

    /**
     * 通过ID查询单条数据
     *
     * @param aId 主键
     * @return 实例对象
     */
    Admin queryById(Integer aId);

    /**
     * 查询指定行数据
     *
     * @param admin 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<Admin> queryAllByLimit(Admin admin, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param admin 查询条件
     * @return 总行数
     */
    long count(Admin admin);

    /**
     * 新增数据
     *
     * @param admin 实例对象
     * @return 影响行数
     */
    int insert(Admin admin);
    Integer sameAccount(String aAccount);
    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Admin> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Admin> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Admin> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<Admin> entities);

    /**
     * 修改数据
     *
     * @param admin 实例对象
     * @return 影响行数
     */
    int update(Admin admin);

    /**
     * 通过主键删除数据
     *
     * @param aId 主键
     * @return 影响行数
     */
    int deleteById(Integer aId);


    /**
     * 通过账号查询管理员
     * @param acc
     * @return
     */
    Admin findByAcc(String acc);
}

