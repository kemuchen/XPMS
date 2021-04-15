package cn.xpms.system.framework.config;

import cn.xpms.system.framework.constant.AuthConstant;
import cn.xpms.system.framework.filter.LoginFilter;
import cn.xpms.system.framework.filter.LogoutFilter;
import cn.xpms.system.framework.util.sys.SysUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @ClassName WebMvcConfig
 * @Desc
 * @Author 柯雷
 * @Date 2019/12/26 12:36
 * @Version 1.0
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    /** 登录拦截器 */
    @Autowired
    LoginFilter loginFilter;

    /** 退出拦截器 */
    @Autowired
    LogoutFilter logoutFilter;

    /** 注销url */
    @Value("${system.login-filter}")
    private String login_filter;

    /** 注销url */
    @Value("${system.logout-filter}")
    private String logout_filter;

    /**
     * @param ：@param registry
     * @return ：void
     * @throws
     * @Title：addInterceptors
     * @Description：添加拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        if (Boolean.parseBoolean(SysUtil.nvl(login_filter))) {
            registry.addInterceptor(loginFilter).addPathPatterns("/**")
                .excludePathPatterns(AuthConstant.LOGIN_URI)
                .excludePathPatterns(AuthConstant.LOGOUT_URI)
                .excludePathPatterns(AuthConstant.CHECK_LOGIN_URI + "/*")
                .excludePathPatterns("/swagger-ui.html/**", "/webjars/**", "/v2/**", "/swagger-resources/**")
                .excludePathPatterns("/error/**");  // 拦截所有
        }
        if (Boolean.parseBoolean(SysUtil.nvl(logout_filter))) {
            registry.addInterceptor(logoutFilter).addPathPatterns("/logout");  // 拦截logout
        }
        super.addInterceptors(registry);
    }

    /**
     * <p>Title：addResourceHandlers</p>
     * <p>Description：配置静态资源访问 </p>
     *
     * @param registry
     * @see #addResourceHandlers(ResourceHandlerRegistry)
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
            .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
            .addResourceLocations("classpath:/META-INF/resources/webjars/");
        super.addResourceHandlers(registry);
    }
}