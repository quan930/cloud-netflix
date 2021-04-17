package cn.lilq.cloudnetflix.cloudconfig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @auther: Li Liangquan
 * @date: 2020/10/26 13:59
 * 服务配置服务器
 * http://localhost:8888/cloud-test/dev/main
 * http://localhost:8888/cloud-eureka-server/dev/main
 * .......
 */

@EnableConfigServer
@SpringBootApplication
public class CloudConfigApplication {
    public static void main(String[] args) {
        SpringApplication.run(CloudConfigApplication.class, args);
    }
}