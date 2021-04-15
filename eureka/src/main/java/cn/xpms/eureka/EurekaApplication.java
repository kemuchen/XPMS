package cn.xpms.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @ClassName EurekaApplication
 * @Desc 注册中心启动类
 * @Author 柯雷
 * @Date 2020/10/28 14:01
 * @Version 1.0
 */
// spring boot启动类
@SpringBootApplication(scanBasePackages = "cn.xpms.**")
// 表示这是一个Eureka注册中心
@EnableEurekaServer
public class EurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaApplication.class, args);
    }

}
