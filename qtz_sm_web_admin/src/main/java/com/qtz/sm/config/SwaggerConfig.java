package com.qtz.sm.config;

import com.mangofactory.swagger.configuration.SpringSwaggerConfig;
import com.mangofactory.swagger.models.dto.ApiInfo;
import com.mangofactory.swagger.paths.SwaggerPathProvider;
import com.mangofactory.swagger.plugin.EnableSwagger;
import com.mangofactory.swagger.plugin.SwaggerSpringMvcPlugin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
/**
 * 
 * <p>Title:SwaggerConfig</p>
 * <p>Description:(SwaggerConfig配置)</p>
 * <p>Copyright: Copyright (c) 2015</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 涂鑫 - changbo.li
 * @version v1.0 2015年4月22日
 */
@Configuration
@EnableSwagger
@EnableWebMvc
public class SwaggerConfig extends WebMvcConfigurerAdapter {

	@Autowired
	private SpringSwaggerConfig springSwaggerConfig;

	/**
	* 链式编程 来定制API样式
	* @return
	*/
	@Bean
	public SwaggerSpringMvcPlugin customImplementation(){
		GtPaths gtPaths = new GtPaths();
		gtPaths.setApiResourcePrefix("qtz_sm");
		SwaggerSpringMvcPlugin swaggerSpringMvcPlugin = new SwaggerSpringMvcPlugin(this.springSwaggerConfig)
				.pathProvider(gtPaths)
//				.apiVersion("0.0.1")
//				.includePatterns(".v(\\d.[0-9]).*")
//				.swaggerGroup("shop")
				.apiInfo(apiInfo())
				.includePatterns(".*?");
		return swaggerSpringMvcPlugin;
	}

	private ApiInfo apiInfo() {
		ApiInfo apiInfo = new ApiInfo(
			"胖胖生活 api",
			"服务器 后台API文档",
			"",
			"xxxx@163.com",
			"My License",
			"My Apps API License URL"
		);
		return apiInfo;
	}

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
	  configurer.enable();
	}
	     
	class GtPaths extends SwaggerPathProvider{
		@Override
		protected String applicationPath() {
			return "/";
		}
		@Override
		protected String getDocumentationPath() {
			return "/";
		}

	}
}
