package cn.lilq.cloudnetflix.cloudtest;

import cn.lilq.cloudnetflix.cloudapicom.pojo.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.annotation.StreamRetryTemplate;
import org.springframework.cloud.stream.messaging.Sink;

/**
 * @auther: Li Liangquan
 * @date: 2020/10/26 15:04
 * 服务测试
 */

@EnableBinding(Sink.class)
@SpringBootApplication
public class CloudTestApplication {
    public static void main(String[] args) {
        SpringApplication.run(CloudTestApplication.class, args);
    }
}
