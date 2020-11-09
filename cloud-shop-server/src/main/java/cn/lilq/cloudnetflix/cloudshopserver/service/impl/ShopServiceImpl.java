package cn.lilq.cloudnetflix.cloudshopserver.service.impl;

import brave.Span;
import brave.Tracer;
import cn.lilq.cloudnetflix.cloudapicom.pojo.Order;
import cn.lilq.cloudnetflix.cloudapicom.pojo.Response;
import cn.lilq.cloudnetflix.cloudshopserver.events.MySourceBean;
import cn.lilq.cloudnetflix.cloudshopserver.service.ShopService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @auther: Li Liangquan
 * @date: 2020/11/6 08:00
 * 添加自定义跨度
 */

@Slf4j
@Service("shopService")
public class ShopServiceImpl implements ShopService {
    @Resource
    private MySourceBean mySourceBean;
    @Resource
    private Tracer tracer;

    @Override
    public Response sendShop(Order order) {
        if (order==null)
            return new Response(400,"order is null",null);
        if (order.getGoodsId()==null || order.getUserId()==null)
            return new Response(400,"content is null",null);
        //自定义跨度
        Span newSpan = tracer.nextSpan().name("Rabbit MQ").start();
        try (Tracer.SpanInScope ws = tracer.withSpanInScope(newSpan.start())){
            mySourceBean.publishOrderChange(order);
            return new Response(200,"successful",null);
        }finally {
            newSpan.finish();
            log.debug("shopService:sendShop()");
        }
    }
}
