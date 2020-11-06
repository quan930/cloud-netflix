package cn.lilq.cloudnetflix.cloudorderserver.events;

import cn.lilq.cloudnetflix.cloudapicom.pojo.Order;
import cn.lilq.cloudnetflix.cloudorderserver.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @auther: Li Liangquan
 * @date: 2020/11/6 18:55
 * shop消息订阅者回调函数 处理订单服务
 */

@Component
public class ShopListener {
    private static final Logger logger = LoggerFactory.getLogger(ShopListener.class);

    @Resource
    private OrderService orderService;

    @StreamListener(Sink.INPUT)
    public void receive(Order order){
        logger.debug("order{}"+"userID"+order.getUserId()+"Goods ID"+order.getGoodsId());
        orderService.addOrder(order);
    }
}