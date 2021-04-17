package cn.lilq.cloudnetflix.cloudorderserver.util;

import cn.lilq.cloudnetflix.cloudapicom.pojo.Order;

/**
 * @auther: Li Liangquan
 * @date: 2020/11/5 08:09
 * 订单转换类 Entity pojo 转换
 */
public class OrderTransformUtil {
    /**
     * 数据库对象转pojo对象
     *
     * @param order entity order
     * @return pojo
     */
    static public Order entityToPojo(cn.lilq.cloudnetflix.cloudorderserver.entity.Order order) {
        return new Order(order.getId(), order.getUserId(), order.getGoodsId(), order.getDate());
    }

    /**
     * pojo对象转数据库对象
     *
     * @param order pojo对象
     * @return entity
     */
    static public cn.lilq.cloudnetflix.cloudorderserver.entity.Order pojoToEntity(Order order) {
        return new cn.lilq.cloudnetflix.cloudorderserver.entity.Order(order.getId(), order.getUserId(), order.getGoodsId(), order.getDate());
    }
}