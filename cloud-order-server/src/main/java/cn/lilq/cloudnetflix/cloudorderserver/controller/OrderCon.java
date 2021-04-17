package cn.lilq.cloudnetflix.cloudorderserver.controller;

import cn.lilq.cloudnetflix.cloudapicom.pojo.Order;
import cn.lilq.cloudnetflix.cloudapicom.pojo.Response;
import cn.lilq.cloudnetflix.cloudorderserver.service.OrderService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @auther: Li Liangquan
 * @date: 2020/11/5 08:17
 * order controller
 */

@Slf4j
@Controller
@DefaultProperties(defaultFallback = "fallback",
        commandProperties = {
                //配置连接超时
                @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000"),
        },
        //指定线程池名称
        threadPoolKey = "orderThreadPool",
        threadPoolProperties = {
                //设置线程池中线程数
                @HystrixProperty(name = "coreSize", value = "50"),
                //单个线程繁忙时间可排队的请求数的队列大小
                @HystrixProperty(name = "maxQueueSize", value = "20")
        })
public class OrderCon {
    @Resource
    private OrderService orderService;

    /**
     * order 列表
     *
     * @return orders
     */
    @ResponseBody
    @RequestMapping(value = "/", method = RequestMethod.GET)
    @HystrixCommand
    public Response orderList() {
        log.debug("OrderCon:orderList()");
        return orderService.listOrder();
    }

    /**
     * 查询指定order
     *
     * @param id order-id
     * @return order
     */
    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @HystrixCommand
    public Response getOrder(@PathVariable String id) {
        log.debug("OrderCon:getOrder(" + id + ")");
        return orderService.findOrderById(new Order(id, null, null, null));
    }


    /**
     * 添加order
     *
     * @param order order
     * @return response
     */
    @ResponseBody
    @RequestMapping(value = "/", method = RequestMethod.POST)
    @HystrixCommand
    public Response addOrder(@RequestBody Order order) {
        log.debug("OrderCon:addOrder(" + order + ")");
        return orderService.addOrder(order);
    }

    /**
     * 修改order
     *
     * @param order order
     * @return response
     */
    @ResponseBody
    @RequestMapping(value = "/", method = RequestMethod.PUT)
    @HystrixCommand
    public Response updateOrder(@RequestBody Order order) {
        log.debug("OrderCon:updateOrder(" + order + ")");
        return orderService.updateOrder(order);
    }

    /**
     * 异常回调
     *
     * @return response
     */
    public Response fallback() {
        log.debug("OrderCon:fallback()");
        return new Response(500, "server error", null);
    }

    /**
     * 测试JWT token 能否顺利传递参数
     *
     * @param test test
     * @return response
     */
    @ResponseBody
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @HystrixCommand
    public Response getTest(@RequestHeader(value = "tmx-test-id") String test) {
        return new Response(200, "successful", test);
    }
}