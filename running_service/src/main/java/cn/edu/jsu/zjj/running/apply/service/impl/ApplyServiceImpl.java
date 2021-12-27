package cn.edu.jsu.zjj.running.apply.service.impl;

import cn.edu.jsu.zjj.running.apply.entity.Apply;
import cn.edu.jsu.zjj.running.apply.dao.ApplyDao;
import cn.edu.jsu.zjj.running.apply.service.ApplyService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 * 用户申请跑腿表(Apply)表服务实现类
 *
 * @author makejava
 * @since 2021-12-26 10:50:46
 */
@Service("applyService")
public class ApplyServiceImpl implements ApplyService {
    @Resource
    private ApplyDao applyDao;

    /**
     * 通过ID查询单条数据
     *
     * @param applyId 主键
     * @return 实例对象
     */
    @Override
    public Apply queryById(Integer applyId) {
        return this.applyDao.queryById(applyId);
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
    public Apply insert(Apply apply) {
        this.applyDao.insert(apply);
        return apply;
    }

    /**
     * 修改数据
     *
     * @param apply 实例对象
     * @return 实例对象
     */
    @Override
    public Apply update(Apply apply) {
        this.applyDao.update(apply);
        return this.queryById(apply.getApplyId());
    }

    /**
     * 通过主键删除数据
     *
     * @param applyId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer applyId) {
        return this.applyDao.deleteById(applyId) > 0;
    }
}
