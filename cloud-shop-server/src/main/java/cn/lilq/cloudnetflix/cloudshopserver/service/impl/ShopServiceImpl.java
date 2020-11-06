package cn.lilq.cloudnetflix.cloudshopserver.service.impl;

import cn.lilq.cloudnetflix.cloudapicom.pojo.Order;
import cn.lilq.cloudnetflix.cloudapicom.pojo.Response;
import cn.lilq.cloudnetflix.cloudshopserver.events.MySourceBean;
import cn.lilq.cloudnetflix.cloudshopserver.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @auther: Li Liangquan
 * @date: 2020/11/6 08:00
 */

@Service("shopService")
public class ShopServiceImpl implements ShopService {
    @Autowired
    private MySourceBean mySourceBean;

    @Override
    public Response sendShop(Order order) {
        if (order==null)
            return new Response(400,"order is null",null);
        if (order.getGoodsId()==null || order.getUserId()==null)
            return new Response(400,"content is null",null);
        mySourceBean.publishOrderChange(order);
        return new Response(200,"successful",null);
    }
}
