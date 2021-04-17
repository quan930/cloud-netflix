package cn.lilq.cloudnetflix.cloudtest.events;

import cn.lilq.cloudnetflix.cloudapicom.pojo.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Component;

/**
 * @auther: Li Liangquan
 * @date: 2020/11/6 10:39
 * shop消息订阅者回调函数
 */

@Component
public class ShopListener {
    private static final Logger logger = LoggerFactory.getLogger(ShopListener.class);

    @StreamListener(Sink.INPUT)
    public void receive(Order order) {
        logger.debug("order{}" + "userID" + order.getUserId() + "Goods ID" + order.getGoodsId());
    }
}
