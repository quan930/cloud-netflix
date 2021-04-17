package cn.lilq.cloudnetflix.cloudshopserver.controller;

import cn.lilq.cloudnetflix.cloudapicom.pojo.Order;
import cn.lilq.cloudnetflix.cloudapicom.pojo.Person;
import cn.lilq.cloudnetflix.cloudapicom.pojo.Response;
import cn.lilq.cloudnetflix.cloudshopserver.service.ShopService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @auther: Li Liangquan
 * @date: 2020/11/5 10:32
 * 商店controller
 */

@Controller
@DefaultProperties(defaultFallback = "fallback",
        commandProperties = {
                //配置连接超时
                @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000"),
        },
        //指定线程池名称
        threadPoolKey = "shopThreadPool",
        threadPoolProperties = {
                //设置线程池中线程数
                @HystrixProperty(name = "coreSize", value = "50"),
                //单个线程繁忙时间可排队的请求数的队列大小
                @HystrixProperty(name = "maxQueueSize", value = "20")
        })
@Slf4j
public class ShopCon {
    @Autowired
    private ShopService shopService;

    @ResponseBody
    @RequestMapping(value = "/", method = RequestMethod.GET)
    @HystrixCommand
    public Response testShop() {
        log.debug("ShopCon:testShop()");
        return new Response(200, "Successful", "hello world");
    }

    @ResponseBody
    @RequestMapping(value = "/", method = RequestMethod.POST)
    @HystrixCommand
    public Response sendShop(@RequestBody Order order) {
        log.debug("ShopCon:sendShop(" + order + ")");
        return shopService.sendShop(order);
    }


    /**
     * 异常回调
     *
     * @return response
     */
    public Response fallback() {
        log.debug("ShopCon:fallback()");
        return new Response(500, "server error", null);
    }
}
