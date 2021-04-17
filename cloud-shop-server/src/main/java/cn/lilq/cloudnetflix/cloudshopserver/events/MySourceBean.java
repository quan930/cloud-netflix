package cn.lilq.cloudnetflix.cloudshopserver.events;

import cn.lilq.cloudnetflix.cloudapicom.pojo.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

/**
 * @auther: Li Liangquan
 * @date: 2020/11/5 23:54
 * 自定义发射器
 */

@Component
public class MySourceBean {
    private Source source;

    private static final Logger logger = LoggerFactory.getLogger(MySourceBean.class);

    @Autowired
    public MySourceBean(Source source) {
        this.source = source;
    }

    public void publishOrderChange(Order order) {
        logger.debug("Sending RabbitMQ message {} for User Id , Goods Id: {}", order.getUserId(), order.getGoodsId());
        source.output().send(MessageBuilder.withPayload(order).build());
    }
}
