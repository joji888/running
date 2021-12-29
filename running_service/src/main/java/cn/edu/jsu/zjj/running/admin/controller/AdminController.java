package cn.edu.jsu.zjj.running.admin.controller;

import cn.edu.jsu.zjj.running.admin.entity.Admin;
import cn.edu.jsu.zjj.running.admin.service.AdminService;
import cn.edu.jsu.zjj.running.utils.Result;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * 管理员表(Admin)表控制层
 *
 * @author PL
 * @since 2021-12-26 10:49:28
 */
@RestController
@RequestMapping("admin")
public class AdminController {
    /**
     * 服务对象
     */
    @Resource
    private AdminService adminService;

    /**
     * 分页查询
     *
     * @param admin 筛选条件
     * @return 查询结果
     */
    @GetMapping
    public Result<ResponseEntity<Page<Admin>>> queryByPage(Admin admin, Integer size,Integer page) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Admin> admins = this.adminService.queryByPage(admin, pageRequest);
        return Result.success(ResponseEntity.ok(admins));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public Result queryById(@PathVariable("id") Integer id) {
        return this.adminService.queryById(id);
    }

    /**
     * 新增数据
     *
     * @param admin 实体
     * @return 新增结果
     */
    @PostMapping
    public Result add(Admin admin) {
        return this.adminService.insert(admin);
    }

    /**
     * 编辑数据
     *
     * @param admin 实体
     * @return 编辑结果
     */
    @PutMapping
    public Result edit(Admin admin) {
        return this.adminService.update(admin);
    }
    /**
     * 修改密码
     *
     * @return 编辑结果
     */
    @PutMapping("updatePwd")
    public Result updatePwd(Integer aId,String oldPwd,String newPwd) {
        return this.adminService.updatePwd(aId,oldPwd,newPwd);
    }

    /**
     * 删除数据
     *
     * @param aId 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public Result deleteById(Integer aId) {
        return this.adminService.deleteById(aId);
    }





    //管理员登陆注册
    @GetMapping("login")
    public Result<HashMap<String, Object>> login(String acc, String pwd){
        return adminService.login(acc,pwd);
    }

    @GetMapping("register")
    public Result register(Admin admin){
        return adminService.register(admin);
    }
}

