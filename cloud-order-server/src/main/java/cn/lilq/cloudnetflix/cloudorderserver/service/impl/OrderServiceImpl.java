package cn.lilq.cloudnetflix.cloudorderserver.service.impl;

import brave.Span;
import brave.Tracer;
import cn.lilq.cloudnetflix.cloudapicom.pojo.Order;
import cn.lilq.cloudnetflix.cloudapicom.pojo.Response;
import cn.lilq.cloudnetflix.cloudorderserver.dao.OrderDAO;
import cn.lilq.cloudnetflix.cloudorderserver.service.OrderService;
import cn.lilq.cloudnetflix.cloudorderserver.util.OrderTransformUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @auther: Li Liangquan
 * @date: 2020/11/5 08:12
 * Order输出类选择com 屏蔽Order Server 实现细节
 * 添加自定义跨度
 */

@Slf4j
@Service("orderService")
public class OrderServiceImpl implements OrderService {
    @Resource
    private OrderDAO orderDAO;
    @Resource
    private Tracer tracer;

    @Override
    public Response addOrder(Order order) {
        if (order.getId() != null)
            return new Response(400, "Id must be null", null);
        if (order.getGoodsId() == null || order.getUserId() == null)
            return new Response(400, "content is null", null);
        if (order.getDate() == null)
            order.setDate(new Date());
        //自定义跨度
        Span newSpan = tracer.nextSpan().name("orderDAO").start();
        try (Tracer.SpanInScope ws = tracer.withSpanInScope(newSpan.start())) {
            return new Response(200, "successful", OrderTransformUtil.entityToPojo(orderDAO.save(OrderTransformUtil.pojoToEntity(order))));
        } finally {
            newSpan.finish();
            log.debug("OrderService:addOrder()");
        }
    }

    @Override
    public Response listOrder() {
        //自定义跨度
        Span newSpan = tracer.nextSpan().name("orderDAO").start();
        try (Tracer.SpanInScope ws = tracer.withSpanInScope(newSpan.start())) {
            List<cn.lilq.cloudnetflix.cloudapicom.pojo.Order> ordersNew = new ArrayList<>();
            List<cn.lilq.cloudnetflix.cloudorderserver.entity.Order> orders = orderDAO.findAll();
            orders.forEach(order ->
                    ordersNew.add(OrderTransformUtil.entityToPojo(order))
            );
            return new Response(200, "successful", ordersNew);
        } finally {
            newSpan.finish();
            log.debug("OrderService:listOrder()");
        }
    }

    @Override
    public Response findOrderById(Order order) {
        if (order.getId() == null)
            return new Response(400, "id is null", null);
        //自定义跨度
        Span newSpan = tracer.nextSpan().name("orderDAO").start();
        try (Tracer.SpanInScope ws = tracer.withSpanInScope(newSpan.start())) {
            return orderDAO.findById(order.getId())
                    .map(value ->
                            new Response(200, "successful", OrderTransformUtil.entityToPojo(value))
                    ).orElseGet(() -> new Response(404, "order is not exist", null));
        } finally {
            newSpan.finish();
            log.debug("OrderService:findOrderById()");
        }
    }

    @Override
    public Response updateOrder(Order order) {
        if (order.getId() == null || order.getUserId() == null || order.getDate() == null || order.getGoodsId() == null) {
            return new Response(400, "content is null", null);
        }
        //自定义跨度
        Span newSpan = tracer.nextSpan().name("orderDAO").start();
        try (Tracer.SpanInScope ws = tracer.withSpanInScope(newSpan.start())) {
            return new Response(200, "successful", OrderTransformUtil.entityToPojo(orderDAO.save(OrderTransformUtil.pojoToEntity(order))));
        } finally {
            newSpan.finish();
            log.debug("OrderService:updateOrder()");
        }
    }
}