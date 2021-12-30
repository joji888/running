package cn.edu.jsu.zjj.running.user.service;

import cn.edu.jsu.zjj.running.user.entity.User;
import cn.edu.jsu.zjj.running.utils.Result;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * 用户表(User)表服务接口
 *
 * @author makejava
 * @since 2021-12-26 11:01:13
 */
public interface UserService {

    /**
     * 通过ID查询单条数据
     *
     * @param uId 主键
     * @return 实例对象
     */
    Result queryById(Integer uId);

    /**
     * 分页查询
     *
     * @param user 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<User> queryByPage(User user, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    Result insert(User user);

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    Result update(User user);

    Result editImg(MultipartFile uploadFIle,Integer uId, String uHeadImg) throws IOException;

    /**
     * 通过主键删除数据
     *
     * @param uId 主键
     * @return 是否成功
     */
    Result deleteById(Integer uId);


    Result register(User user);

    Result updatePwd(Integer uId, String oldPwd, String newPwd);

    Result login(String acc, String pwd);
}
