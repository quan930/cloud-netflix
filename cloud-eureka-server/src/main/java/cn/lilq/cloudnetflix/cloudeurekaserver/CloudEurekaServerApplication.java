package cn.lilq.cloudnetflix.cloudeurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @auther: Li Liangquan
 * @date: 2020/10/26 15:20
 * 服务注册中心
 * http://eureka7001.com:7001/
 * http://eureka7002.com:7002/
 */

@EnableEurekaServer
@SpringBootApplication
public class CloudEurekaServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(CloudEurekaServerApplication.class,args);
    }
}
