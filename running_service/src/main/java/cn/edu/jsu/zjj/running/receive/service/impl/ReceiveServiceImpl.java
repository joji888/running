package cn.edu.jsu.zjj.running.receive.service.impl;

import cn.edu.jsu.zjj.running.receive.entity.Receive;
import cn.edu.jsu.zjj.running.receive.dao.ReceiveDao;
import cn.edu.jsu.zjj.running.receive.service.ReceiveService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 * 跑腿接单表(Receive)表服务实现类
 *
 * @author makejava
 * @since 2021-12-26 11:00:24
 */
@Service("receiveService")
public class ReceiveServiceImpl implements ReceiveService {
    @Resource
    private ReceiveDao receiveDao;

    /**
     * 通过ID查询单条数据
     *
     * @param rId 主键
     * @return 实例对象
     */
    @Override
    public Receive queryById(Integer rId) {
        return this.receiveDao.queryById(rId);
    }

    /**
     * 分页查询
     *
     * @param receive 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<Receive> queryByPage(Receive receive, PageRequest pageRequest) {
        long total = this.receiveDao.count(receive);
        return new PageImpl<>(this.receiveDao.queryAllByLimit(receive, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param receive 实例对象
     * @return 实例对象
     */
    @Override
    public Receive insert(Receive receive) {
        this.receiveDao.insert(receive);
        return receive;
    }

    /**
     * 修改数据
     *
     * @param receive 实例对象
     * @return 实例对象
     */
    @Override
    public Receive update(Receive receive) {
        this.receiveDao.update(receive);
        return this.queryById(receive.getRId());
    }

    /**
     * 通过主键删除数据
     *
     * @param rId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer rId) {
        return this.receiveDao.deleteById(rId) > 0;
    }
}
