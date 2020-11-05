package cn.lilq.cloudnetflix.cloudapicom.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * @auther: Li Liangquan
 * @date: 2020/11/4 20:06
 */

public class Order implements Serializable {
    private String id;//订单编号
    private String userId;//用户Id
    private String goodsId;//商品id
    private Date date;//订单生成时间

    public Order(String id, String userId, String goodsId, Date date) {
        this.id = id;
        this.userId = userId;
        this.goodsId = goodsId;
        this.date = date;
    }

    public Order() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id) &&
                Objects.equals(userId, order.userId) &&
                Objects.equals(goodsId, order.goodsId) &&
                Objects.equals(date, order.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, goodsId, date);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", goodsId='" + goodsId + '\'' +
                ", date=" + date +
                '}';
    }
}