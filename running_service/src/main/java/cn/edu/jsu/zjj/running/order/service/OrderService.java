package cn.edu.jsu.zjj.running.order.service;

import cn.edu.jsu.zjj.running.order.entity.Order;
import cn.edu.jsu.zjj.running.utils.Result;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * 订单表(Order)表服务接口
 *
 * @author makejava
 * @since 2021-12-26 10:52:50
 */
public interface OrderService {

    /**
     * 通过ID查询单条数据
     *
     * @param oId 主键
     * @return 实例对象
     */
    Result queryById(Integer oId);

    /**
     * 分页查询
     *
     * @param order 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<Order> queryByPage(Order order, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param order 实例对象
     * @return 实例对象
     */
    Result insert(MultipartFile file,Order order) throws IOException;

    /**
     * 修改数据
     *
     * @param order 实例对象
     * @return 实例对象
     */
    Result update(Order order);

    /**
     * 通过主键删除数据
     *
     * @param oId 主键
     * @return 是否成功
     */
    Result deleteById(Integer oId);

    Result editImg(MultipartFile multipartFile,Integer oId, String image) throws IOException;

    Result queryByState(Integer oState);
}
