package cn.xpms.paas;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @ClassName PaasApplication
 * @Desc
 * @Author 柯雷
 * @Date 2020/11/12 11:25
 * @Version 1.0
 */
@SpringBootApplication(scanBasePackages = "cn.xpms.**")
@MapperScan({"cn.xpms.**.dao"})
@EnableCaching
@EnableTransactionManagement
public class PaasApplication {

    /**
     * @Description: 入口main方法
     * @Params: [args]
     * @return: void
     * @Author: 柯雷
     * @Date: 2020/11/17 9:04
     */
    public static void main(String[] args) {
        SpringApplication.run(PaasApplication.class, args);
    }
}