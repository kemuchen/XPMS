package cn.xpms.system.framework.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Package: cn.xpms
 * @ClassName: SwaggerConfig
 * @Description:TODO
 * @Author: Yk
 * @Date: 2020年4月12日 下午1:58:05
 * @Version 1.0
 * @Copyright:
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Value("${system.swagger.path-mapping}")
	private String pathMapping;

	@Value("${system.swagger.base-package}")
	private String basePackage;

	@Value("${system.swagger.title}")
	private String title;

	@Value("${system.swagger.description}")
	private String description;

	@Value("${system.swagger.version}")
	private String version;

	@Value("${system.swagger.licene.title}")
	private String licenseTtile;

	@Value("${system.swagger.licene.url}")
	private String licenseUrl;

	@Bean
	public Docket createRestApi() {
		return new Docket(DocumentationType.SWAGGER_2)
			.pathMapping(pathMapping)
			.select()
			.apis(RequestHandlerSelectors.basePackage(basePackage))
			.paths(PathSelectors.any())
			.build()
			.apiInfo(
				new ApiInfoBuilder().title(title)
					.description(description)
					.version(version).license(licenseTtile)
					.licenseUrl(licenseUrl).build());
	}
}
