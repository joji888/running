package cn.edu.jsu.zjj.running.admin.service;

import cn.edu.jsu.zjj.running.admin.entity.Admin;
import cn.edu.jsu.zjj.running.utils.Result;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.HashMap;

/**
 * 管理员表(Admin)表服务接口
 *
 * @author makejava
 * @since 2021-12-26 10:49:29
 */
public interface AdminService {

    /**
     * 通过ID查询单条数据
     *
     * @param aId 主键
     * @return 实例对象
     */
    Result queryById(Integer aId);

    /**
     * 分页查询
     *
     * @param admin 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<Admin> queryByPage(Admin admin, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param admin 实例对象
     * @return 实例对象
     */
    Result insert(Admin admin);

    /**
     * 修改数据
     *
     * @param admin 实例对象
     * @return 实例对象
     */
    Result update(Admin admin);

    /**
     * 通过主键删除数据
     *
     * @param aId 主键
     * @return 是否成功
     */
    Result deleteById(Integer aId);




    Result<HashMap<String, Object>> login(String acc, String pwd);

    Result register(Admin admin);

    Result updatePwd(Integer aId, String oldPwd, String newPwd);
}
