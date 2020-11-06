package cn.lilq.cloudnetflix.cloudshopserver.service;

import cn.lilq.cloudnetflix.cloudapicom.pojo.Order;
import cn.lilq.cloudnetflix.cloudapicom.pojo.Response;

/**
 * @auther: Li Liangquan
 * @date: 2020/11/6 07:58
 * shop 服务
 */
public interface ShopService {
    /**
     * 发布订单消息
     * @param order 订单
     */
    Response sendShop(Order order);
}
