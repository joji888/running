package cn.edu.jsu.zjj.running.receive.service;

import cn.edu.jsu.zjj.running.receive.entity.Receive;
import cn.edu.jsu.zjj.running.utils.Result;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * 跑腿接单表(Receive)表服务接口
 *
 * @author makejava
 * @since 2021-12-26 11:00:24
 */
public interface ReceiveService {

    /**
     * 通过ID查询单条数据
     *
     * @param rId 主键
     * @return 实例对象
     */
    Result queryById(Integer rId);

    /**
     * 分页查询
     *
     * @param receive 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<Receive> queryByPage(Receive receive, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param receive 实例对象
     * @return 实例对象
     */
    Result insert(Receive receive);

    /**
     * 修改数据
     *
     * @param receive 实例对象
     * @return 实例对象
     */
    Result update(Receive receive);

    /**
     * 通过主键删除数据
     *
     * @param rId 主键
     * @return 是否成功
     */
    Result deleteById(Integer rId);

    Result addById(Receive receive);

    Result<Receive> queryBySeate(Integer ruId, Integer rSeate);
}
