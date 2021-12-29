package cn.edu.jsu.zjj.running.user.service.impl;

import cn.edu.jsu.zjj.running.upload.UploadFile;
import cn.edu.jsu.zjj.running.user.entity.User;
import cn.edu.jsu.zjj.running.user.dao.UserDao;
import cn.edu.jsu.zjj.running.user.service.UserService;
import cn.edu.jsu.zjj.running.utils.Result;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * 用户表(User)表服务实现类
 *
 * @author makejava
 * @since 2021-12-26 11:01:13
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    /**
     * 通过ID查询单条数据
     *
     * @param uId 主键
     * @return 实例对象
     */
    @Override
    public Result queryById(Integer uId) {
        if (uId <0){
            return Result.error("ID不能为空");
        }
        User queryById = this.userDao.queryById(uId);
        if (queryById == null){
            return Result.error("用户不存在");
        }
        return Result.success(queryById);
    }

    /**
     * 分页查询
     *
     * @param user 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<User> queryByPage(User user, PageRequest pageRequest) {
        long total = this.userDao.count(user);
        return new PageImpl<>(this.userDao.queryAllByLimit(user, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    @Override
    public Result insert(User user) {
        if (!user.getuRole().equals("user") && !user.getuRole().equals("running")){
            return Result.error("用户角色名格式错误");
        }
        if (user.getUNick() == null || user.getUNick().equals("")){
            return Result.error("昵称不能为空");
        }
        if (user.getUAccount() == null || user.getUAccount().equals("")){
            return Result.error("账号不能为空");
        }
        if (user.getUPassword() == null || user.getUPassword().equals("")){
            return Result.error("密码不能为空");
        }
        if (this.userDao.find(user)>0){
            return Result.error("账号已被注册，不能重复");
        }

        Integer insert = this.userDao.insert(user);
        if (insert >0){
            return Result.success("新增数据成功");
        }
        return Result.error("新增数据失败");
    }

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    @Override
    public Result update(User user) {
        if (!user.getuRole().equals("user") && !user.getuRole().equals("running")){
            return Result.error("用户角色名格式错误");
        }

        if (user.getUId() == null || user.getUId().equals("")){
            return Result.error("ID不能为空");
        }
        if (user.getUNick() == null || user.getUNick().equals("")){
            return Result.error("昵称不能为空");
        }
        if (user.getUAccount() == null || user.getUAccount().equals("")){
            return Result.error("账号不能为空");
        }
        if (user.getUPassword() == null || user.getUPassword().equals("")){
            return Result.error("密码不能为空");
        }
        Integer update = this.userDao.update(user);
        if (update > 0){
            return Result.success("修改数据成功");
        }
            return Result.error("修改数据失败");
    }

    public Result editImg(MultipartFile uploadFIle,Integer uId , String uHeadImg) throws IOException {
        if (uId ==null || uId<1){
            return Result.error("用户ID不存在");
        }
        if (uHeadImg ==null || uHeadImg.equals("")){
            return Result.error("用户图片不能为空");
        }

        UploadFile uploadFile = new UploadFile();
        uHeadImg = uploadFile.uploadFile(uploadFIle, uHeadImg);

        User user = new User();
        user.setUId(uId);
        user.setUHeadImg(uHeadImg);
        Integer update = userDao.update(user);
        if (update > 0){
            return Result.success("用户头像修改成功");
        }
        return Result.error("用户头像修改失败");
    }

    /**
     * 通过主键删除数据
     *
     * @param uId 主键
     * @return 是否成功
     */
    @Override
    public Result deleteById(Integer uId) {
        if (uId <1){
            return Result.error("ID不存在");
        }
        Integer deleteById = this.userDao.deleteById(uId);
        if (deleteById > 0){
            return Result.success("删除成功");
        }
            return Result.error("删除失败");
    }

<<<<<<< HEAD
=======
    @Override
    public Result updatePwd(Integer uId,String oldPwd,String newPwd){
        if (uId ==null || uId < 1){
            return Result.error("用户名格式错误");
        }
        if (oldPwd == null || oldPwd.equals("")){
            return Result.error("旧密码格式有误");
        }
        if (newPwd == null || newPwd.equals("") || newPwd.length()<6){
            return Result.error("新密码格式有误");
        }
        if (oldPwd.equals(newPwd)){
            return Result.error("旧密码和新密码不能相同");
        }

        User user = this.userDao.queryById(uId);
        if (!user.getUPassword().equals(oldPwd)){
            return Result.error("旧密码错误");
        }
        user.setUPassword(newPwd);

        Integer update = this.userDao.update(user);
        if (update > 0){
            return Result.error("修改密码成功");
        }
            return Result.error("修改密码失败");

    }

    @Override
    public Result register(User user){
        if (user.getUAccount() == null || user.getUAccount().equals("")){
            return Result.error("账号不能为空");
        }
        if (user.getUPassword() == null || user.getUPassword().equals("")){
            return Result.error("密码不能为空");
        }

        Integer insert = this.userDao.insert(user);
        if (insert >0){
            return Result.error("注册成功");
        }
            return Result.error("注册失败");

    }


>>>>>>> 9176d542710b5cd397d95bc6e2bf42be0c1637bc

}
