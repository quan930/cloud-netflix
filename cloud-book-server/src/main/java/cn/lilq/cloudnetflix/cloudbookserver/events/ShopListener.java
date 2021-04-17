package cn.lilq.cloudnetflix.cloudbookserver.events;

import cn.lilq.cloudnetflix.cloudapicom.pojo.Book;
import cn.lilq.cloudnetflix.cloudapicom.pojo.Order;
import cn.lilq.cloudnetflix.cloudbookserver.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @auther: Li Liangquan
 * @date: 2020/11/6 19:09
 * shop消息订阅者回调函数 处理书籍 库存
 */

@Slf4j
@Component
public class ShopListener {
    @Resource
    private BookService bookService;

    private static final Logger logger = LoggerFactory.getLogger(ShopListener.class);

    @StreamListener(Sink.INPUT)
    public void receive(Order order) {
        log.debug("ShopListener:receive(" + order + ")");
        bookService.updateBookRepertory(new Book(order.getGoodsId(), null, null, -1));
    }
}