package cn.edu.jsu.zjj.running.admin.service.impl;

import cn.edu.jsu.zjj.running.admin.entity.Admin;
import cn.edu.jsu.zjj.running.admin.dao.AdminDao;
import cn.edu.jsu.zjj.running.admin.service.AdminService;
import cn.edu.jsu.zjj.running.config.MyFilter;
import cn.edu.jsu.zjj.running.utils.Encryption;
import cn.edu.jsu.zjj.running.utils.Result;
import cn.edu.jsu.zjj.running.utils.Token;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.HashMap;

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
        if (admin.getaRole()==null || admin.getaRole().equals("")){
            return Result.error("角色不能为空");
        }
        if (!admin.getaRole().equals("root") && !admin.getaRole().equals("admin")){
            return Result.error("角色只有root和admin");
        }
        if (admin.getAAccount()==null || admin.getAAccount().equals("")){
            return Result.error("账号不能为空");
        }
        if (this.adminDao.sameAccount(admin.getAAccount())>0){
            return Result.error("账号已经存在");
        }
        if (admin.getAPassword()==null || admin.getAPassword().equals("")){
            return Result.error("密码不能为空");
        }
        admin.setAPassword(Encryption.getSah256(Encryption.getSah256(admin.getAPassword())));
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
        if (admin.getAId()==null || admin.getAId()<1){
            return Result.error("管理员ID不能为空");
        }
        if (admin.getaRole()==null || admin.getaRole().equals("")){
            return Result.error("角色不能为空");
        }
        if (!admin.getaRole().equals("root") && !admin.getaRole().equals("admin")){
            return Result.error("角色只有root和admin");
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




    public Result<HashMap<String, Object>> login(String acc, String pwd){
        if (acc==null||"".equals(acc)){
            return Result.error("账号不能为空");
        }
        if (pwd==null||"".equals(pwd)){
            return Result.error("密码不能为空");
        }

        Admin admin = adminDao.findByAcc(acc);//通过用户账号或者手机号查询用户

        if (admin==null){//判断用户是否存在
            return Result.error("账号或者密码不正确");
        }
        if (!admin.getAPassword().equals(Encryption.getSah256(Encryption.getSah256(pwd)))) {//判断用户密码是否正确
            return Result.error("账号或者密码不正确");
        }

        //登录成功
        String token = Encryption.getToken(admin.getAAccount(), hashCode());
        Token token_o= new Token();
        token_o.setToken(token);
        token_o.setAdmin(admin);
        token_o.setEndTime(System.currentTimeMillis()+1000*60*60*3);
        MyFilter.tokenMap.put(token,token_o);

        System.out.println(token);
        admin.setAPassword("");
        HashMap<String, Object> map = new HashMap<>();
        map.put("admin",admin);
        map.put("token",token);
        return Result.success(map);
    }

    public Result register(Admin admin){
//        if (admin.getuName() == null || admin.getuName().equals("")){
//            return Result.error("用户名不能为空");
//        }
        if (admin.getAAccount() == null || admin.getAAccount().equals("")){
            return Result.error("用户账号不能为空");
        }
        if (admin.getAPassword() == null || admin.getAPassword().equals("")){
            return Result.error("用户密码不能为空");
        }
        Integer register =adminDao.insert(admin);
        if (register>0){
            return Result.error("注册成功");
        }else {
            return Result.error("注册失败");
        }
    }

    @Override
    public Result updatePwd(Integer aId, String oldPwd, String newPwd) {
        if (aId==null||aId<1){
            return Result.error("管理员ID不能为空！");
        }
        if (oldPwd==null||oldPwd.equals("")){
            return Result.error("旧密码有误！");
        }
        if (newPwd==null||newPwd.equals("")||newPwd.length()<6){
            return Result.error("新密码有误！");
        }
        if (oldPwd.equals(newPwd)){
            return Result.error("两次密码不一致！");
        }

        Admin admin = adminDao.queryById(aId);
        if (!admin.getAPassword().equals(Encryption.getSah256(Encryption.getSah256(oldPwd)))) {
            return Result.error("不正确！");
        }
        admin.setAPassword(Encryption.getSah256(Encryption.getSah256(newPwd)));

        int update = adminDao.update(admin);
        if (update<1){
            return Result.error("修改密码到数据库时有误！");
        }
        return Result.success("修改密码成功！");
    }
}
