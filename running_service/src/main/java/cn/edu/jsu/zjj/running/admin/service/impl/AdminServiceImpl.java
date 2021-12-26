package cn.edu.jsu.zjj.running.admin.service.impl;

import cn.edu.jsu.zjj.running.admin.entity.Admin;
import cn.edu.jsu.zjj.running.admin.dao.AdminDao;
import cn.edu.jsu.zjj.running.admin.service.AdminService;
import cn.edu.jsu.zjj.running.utils.Result;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 * 管理员表(Admin)表服务实现类
 *
 * @author PL
 * @since 2021-12-26 10:49:29
 */
@Service("adminService")
public class AdminServiceImpl implements AdminService {
    @Resource
    private AdminDao adminDao;

    /**
     * 通过ID查询单条数据
     *
     * @param aId 主键
     * @return 实例对象
     */
    @Override
    public Result queryById(Integer aId) {
        if (aId==null || aId<1){
            return Result.error("管理员ID不能为空或0");
        }
        return Result.success(this.adminDao.queryById(aId));
    }

    /**
     * 分页查询
     *
     * @param admin 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<Admin> queryByPage(Admin admin, PageRequest pageRequest) {
        long total = this.adminDao.count(admin);
        return new PageImpl<>(this.adminDao.queryAllByLimit(admin, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param admin 实例对象
     * @return 实例对象
     */
    @Override
    public Result insert(Admin admin) {
        if (admin.getAAccount()==null || admin.getAAccount().equals("")){
            return Result.error("账号不能为空");
        }
        if (admin.getAPassword()==null || admin.getAPassword().equals("")){
            return Result.error("密码不能为空");
        }
        Integer insert = adminDao.insert(admin);
        if (insert>0){
            return Result.success("添加成功");
        }else{
            return Result.error("添加失败");
        }

    }

    /**
     * 修改数据
     *
     * @param admin 实例对象
     * @return 实例对象
     */
    @Override
    public Result update(Admin admin) {
        if (admin.getAId()==null || admin.getAId().equals("")){
            return Result.error("管理员ID不能为空");
        }
        Integer update = this.adminDao.update(admin);
        if (update>0){
            return Result.success("修改成功");
        }
            return Result.error("修改失败");

    }

    /**
     * 通过主键删除数据
     *
     * @param aId 主键
     * @return 是否成功
     */
    @Override
    public Result deleteById(Integer aId) {
        if (aId==null || aId<1){
            return Result.error("管理员ID不能为空或小于0");
        }
        Integer del = this.adminDao.deleteById(aId);
        if (del>0){
            return Result.success("删除成功");
        }
        return Result.error("删除失败");
    }
}
