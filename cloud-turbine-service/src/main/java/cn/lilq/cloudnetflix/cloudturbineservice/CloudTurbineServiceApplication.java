package cn.lilq.cloudnetflix.cloudturbineservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

/**
 * @auther: Li Liangquan
 * @date: 2020/10/27 15:16
 * 监控聚合
 * http://127.0.0.1:9002/turbine.stream
 */


@EnableTurbine
@EnableDiscoveryClient
@SpringBootApplication
public class CloudTurbineServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(CloudTurbineServiceApplication.class, args);
    }
}