package cn.lilq.cloudnetflix.cloudzuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @auther: Li Liangquan
 * @date: 2020/10/27 20:29
 * http://127.0.0.1/actuator/routes
 */

@EnableZuulProxy
@SpringBootApplication
public class CloudZuulApplication {
    public static void main(String[] args) {
        SpringApplication.run(CloudZuulApplication.class,args);
    }
}