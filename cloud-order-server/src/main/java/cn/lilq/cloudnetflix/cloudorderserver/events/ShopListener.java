package cn.lilq.cloudnetflix.cloudorderserver.events;

import cn.lilq.cloudnetflix.cloudapicom.pojo.Order;
import cn.lilq.cloudnetflix.cloudorderserver.service.OrderService;
import lombok.extern.slf4j.Slf4j;
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

@Slf4j
@Component
public class ShopListener {
    @Resource
    private OrderService orderService;

    @StreamListener(Sink.INPUT)
    public void receive(Order order) {
        log.debug("ShopListener:receive(" + order + ")");
        orderService.addOrder(order);
    }
}