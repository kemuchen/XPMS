package cn.xpms.member;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @ClassName AuthConstant
 * @Desc 会员子系统启动类
 * @Author 柯雷
 * @Date 2020/10/28 14:06
 * @Version 1.0
 */
@SpringBootApplication(scanBasePackages = "cn.xpms.**")
@MapperScan({"cn.xpms.**.dao"})
@EnableCaching
@EnableTransactionManagement
public class MemberApplication extends SpringBootServletInitializer {

    /**
     * @Description: 入口main方法
     * @Params: [args]
     * @return: void
     * @Author: 柯雷
     * @Date: 2019/12/25 9:35
     */
    public static void main(String[] args) {
        // 启动时设置spring上下文
        SpringApplication.run(MemberApplication.class, args);
    }
}
