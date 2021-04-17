package cn.lilq.cloudnetflix.cloudorderserver.service;

import cn.lilq.cloudnetflix.cloudapicom.pojo.Order;
import cn.lilq.cloudnetflix.cloudapicom.pojo.Response;

/**
 * @auther: Li Liangquan
 * @date: 2020/11/5 08:11
 * 订单service
 */
public interface OrderService {
    /**
     * 增加订单
     *
     * @param order 用户对象
     * @return response
     */
    Response addOrder(Order order);

    /**
     * 订单列表
     *
     * @return Response data List<Order>
     */
    Response listOrder();

    /**
     * 根据id 查询订单
     *
     * @param order 需要order id
     * @return Response data Order
     */
    Response findOrderById(Order order);

    /**
     * 更新订单
     *
     * @param order 需要更新的订单
     * @return Response data Order(更新后)
     */
    Response updateOrder(Order order);
}
