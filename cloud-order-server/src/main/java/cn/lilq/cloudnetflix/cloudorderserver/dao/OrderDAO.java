package cn.lilq.cloudnetflix.cloudorderserver.dao;

import cn.lilq.cloudnetflix.cloudorderserver.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @auther: Li Liangquan
 * @date: 2020/11/5 08:05
 * 订单 data jpa
 */
@Repository
public interface OrderDAO extends JpaRepository<Order,String> {
}