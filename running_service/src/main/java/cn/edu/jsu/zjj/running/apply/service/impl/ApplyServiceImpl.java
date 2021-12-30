package cn.edu.jsu.zjj.running.apply.service.impl;

import cn.edu.jsu.zjj.running.admin.entity.Admin;
import cn.edu.jsu.zjj.running.apply.entity.Apply;
import cn.edu.jsu.zjj.running.apply.dao.ApplyDao;
import cn.edu.jsu.zjj.running.apply.service.ApplyService;
import cn.edu.jsu.zjj.running.user.dao.UserDao;
import cn.edu.jsu.zjj.running.user.entity.User;
import cn.edu.jsu.zjj.running.utils.Result;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 * 用户申请跑腿表(Apply)表服务实现类
 *
 * @author PL
 * @since 2021-12-26 10:50:46
 */
@Service("applyService")
public class ApplyServiceImpl implements ApplyService {

    private ApplyDao applyDao;
    private UserDao userDao;
    public ApplyServiceImpl(ApplyDao applyDao, UserDao userDao) {
        this.applyDao = applyDao;
        this.userDao = userDao;
    }

    /**
     * 通过ID查询单条数据
     *
     * @param applyId 主键
     * @return 实例对象
     */
    @Override
    public Result queryById(Integer applyId) {
        if (applyId==null || applyId<1){
            return Result.error("用户申请Id不能为空或等于0");
        }
        return Result.success(this.applyDao.queryById(applyId));
    }

    /**
     * 分页查询
     *
     * @param apply 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<Apply> queryByPage(Apply apply, PageRequest pageRequest) {
        long total = this.applyDao.count(apply);
        return new PageImpl<>(this.applyDao.queryAllByLimit(apply, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param apply 实例对象
     * @return 实例对象
     */
    @Override
    public Result insert(Apply apply) {

        if (apply.getApplyTime()==null || apply.getApplyTime().equals("")){
            return Result.error("用户申请时间不能为空");
        }
        if (apply.getApplyState()==null || apply.getApplyState().equals("")){
            return Result.error("用户申请状态不能为空");
        }
        if (apply.getApplyRejectInfo()==null || apply.getApplyRejectInfo().equals("")){
            return Result.error("驳回信息不能为空");
        }
        if (apply.getApplyIdentityCode()==null || apply.getApplyIdentityCode().equals("")){
            return Result.error("用户申请身份证不能为空");
        }
        if (apply.getApplyIdentityCodeFront()==null || apply.getApplyIdentityCodeFront().equals("")){
            return Result.error("用户申请身份证正面不能为空");
        }
        if (apply.getApplyIdentityCodeBack()==null || apply.getApplyIdentityCodeBack().equals("")){
            return Result.error("用户申请身份证背面不能为空");
        }
        Integer insert = applyDao.insert(apply);
        if (insert>0){
            return Result.success("添加成功");
        }else{
            return Result.error("添加失败");
        }
    }

    /**
     * 修改数据
     *
     * @param apply 实例对象
     * @return 实例对象
     */
    @Override
    public Result update(Apply apply) {

        if (apply.getApplyId()==null || apply.getApplyId()<1){
            return Result.error("用户申请ID不能为空");
        }
        if (apply.getApplyTime()==null || apply.getApplyTime().equals("")){
            return Result.error("用户申请时间不能为空");
        }
//        if (apply.getApplyRejectInfo()==null || apply.getApplyRejectInfo().equals("")){
//            return Result.error("驳回信息不能为空");
//        }
        if (apply.getApplyState()==null || apply.getApplyState().equals("")){
            return Result.error("用户申请状态不能为空");
        }
        if (apply.getApplyIdentityCode()==null || apply.getApplyIdentityCode().equals("")){
            return Result.error("用户申请身份证不能为空");
        }
        if (apply.getApplyIdentityCodeFront()==null || apply.getApplyIdentityCodeFront().equals("")){
            return Result.error("用户申请身份证正面不能为空");
        }
        if (apply.getApplyIdentityCodeBack()==null || apply.getApplyIdentityCodeBack().equals("")){
            return Result.error("用户申请身份证背面不能为空");
        }

        if (apply.getApplyState().equals("2")){
            User user = userDao.queryById(apply.getuId());
            user.setuRole("running");
            Integer update = this.userDao.update(user);
            if (update>0){
                return Result.success("审核通过");
            }
            return Result.error("审核失败");
        }
        Integer update = this.applyDao.update(apply);
        if (update>0){
            return Result.success("修改成功");
        }
        return Result.error("修改失败");
    }

    /**
     * 通过主键删除数据
     *
     * @param applyId 主键
     * @return 是否成功
     */
    @Override
    public Result deleteById(Integer applyId) {
        if (applyId==null || applyId<1){
            return Result.error("用户申请ID不能为空或小于0");
        }
        User user = userDao.findUserByApplyId(applyId);
        if (user.getuRole().equals("running")) {
            user.setuRole("users");
        }
        Integer del = this.applyDao.deleteById(applyId);
        if (del>0){
            userDao.update(user);
            return Result.success("删除成功");
        }
        return Result.error("删除失败");
    }


}
