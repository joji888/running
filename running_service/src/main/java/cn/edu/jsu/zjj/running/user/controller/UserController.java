package cn.edu.jsu.zjj.running.user.controller;

import cn.edu.jsu.zjj.running.order.entity.Order;
import cn.edu.jsu.zjj.running.user.entity.User;
import cn.edu.jsu.zjj.running.user.service.UserService;
import cn.edu.jsu.zjj.running.utils.Encryption;
import cn.edu.jsu.zjj.running.utils.MailSend;
import cn.edu.jsu.zjj.running.utils.RandomUtil;
import cn.edu.jsu.zjj.running.utils.Result;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * 用户表(User)表控制层
 *
 * @author makejava
 * @since 2021-12-26 11:01:13
 */
@RestController
@RequestMapping("user")
public class UserController {
    /**
     * 服务对象
     */
    private UserService userService;

    private MailSend mailSend;

    public UserController(UserService userService, MailSend mailSend) {
        this.userService = userService;
        this.mailSend = mailSend;
    }

    /**
     * 分页查询
     *
     * @param user 筛选条件
     * @return 查询结果
     */
    @GetMapping
    public Result<ResponseEntity<Page<User>>> queryByPage(User user, Integer size,Integer page) {
        PageRequest pageRequest =PageRequest.of(page,size);
        Page<User> users = this.userService.queryByPage(user, pageRequest);
        return Result.success(ResponseEntity.ok(users));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public Result<User> queryById(@PathVariable("id") Integer id) {
        return this.userService.queryById(id);
    }

    /**
     * 新增数据
     *
     * @param user 实体
     * @return 新增结果
     */
    @PostMapping
    public Result add(User user) {
        return this.userService.insert(user);
    }

    /**
     * 编辑数据
     *
     * @param user 实体
     * @return 编辑结果
     */
    @PutMapping
    public Result edit(User user) {
        return this.userService.update(user);
    }

    @PostMapping("editImg")
    public Result editImg(MultipartFile uploadFIle,Integer uId , String uHeadImg) throws IOException {
        return this.userService.editImg(uploadFIle,uId,uHeadImg);
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public Result deleteById(Integer id) {
        return this.userService.deleteById(id);
    }

    @PostMapping("login")
    public Result login(String acc,String pwd){
        return userService.login(acc,pwd);
    }

    @PostMapping("register")
    public Result register(User user){
        return this.userService.register(user);
    }

    @PostMapping("verifyUser")
    public Result verifyUser(User user, String uPassword2,String verifyCodeData,String Token){
        if (user.getUEmail()==null||user.getUEmail().equals("")){
            return Result.error("邮箱不能为空");
        }
        if (!user.getUPassword().equals(uPassword2)){
            return Result.error("两次密码不一致");
        }

        PageRequest pageRequest = PageRequest.of(0, 1);
        Page<User> users = this.userService.queryByPage(user, pageRequest);
        if (users.getSize()>1) {
            return Result.error("该邮箱或者手机号已被注册");
        }

        //判断有没有验证码
        if (verifyCodeData!=null&&!verifyCodeData.equals("")&&Token!=null&&!Token.equals("")){
            if (Encryption.getSah256(Encryption.getSah256(verifyCodeData)).equals(Token)){
                userService.register(user);
            }else {
                return Result.error("验证码错误！");
            }
        }else {//没有验证码
            if (Token!=null&&!Token.equals("")){
                return Result.success("","请输入验证码");
            }
            String random = RandomUtil.random();
            mailSend.send(user.getUEmail(),random);
            return Result.success( Encryption.getSah256(Encryption.getSah256(random)),"没有验证码");
        }

        return Result.success("成功");
    }

    @PutMapping("updatePwd")
    public Result updatePwd(Integer uId,String oldPwd,String newPwd){
        return this.userService.updatePwd(uId,oldPwd,newPwd);
    }

}

