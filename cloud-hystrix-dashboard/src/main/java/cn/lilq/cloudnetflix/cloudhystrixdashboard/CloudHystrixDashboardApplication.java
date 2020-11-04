package cn.lilq.cloudnetflix.cloudhystrixdashboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * @auther: Li Liangquan
 * @date: 2020/10/27 14:50
 * 服务监控 仪表盘
 * http://127.0.0.1:9001/hystrix
 */


@SpringBootApplication
@EnableHystrixDashboard
public class CloudHystrixDashboardApplication {
    public static void main(String[] args) {
        SpringApplication.run(CloudHystrixDashboardApplication.class,args);
    }
}
