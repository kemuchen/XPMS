package cn.xpms.system;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(scanBasePackages = "cn.xpms")
@MapperScan(basePackages = "cn.xpms.**.dao.**")
//@EnableEurekaClient 和 @EnableDiscoveryClient 都是让eureka发现该服务并注册到eureka上的注解
//相同点：都能让注册中心Eureka发现，并将该服务注册到注册中心上；
//不同点：@EnableEurekaClient只适用于Eureka作为注册中心，而@EnableDiscoveryClient可以是其他注册中心；
@EnableEurekaClient
//表示开启Fegin客户端
@EnableFeignClients
public class SystemApplication {

    /**
     * @Description: 入口main方法
     * @Params: [args]
     * @return: void
     * @Author: 柯雷
     * @Date: 2019/12/25 9:35
     */
    public static void main(String[] args) {
        // 启动时设置spring上下文
        SpringApplication.run(SystemApplication.class, args);
    }
}