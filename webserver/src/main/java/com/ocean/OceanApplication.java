package com.ocean;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author xingzhe
 * @description
 * @update 2014年6月20日 上午10:48:42
 */

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class OceanApplication {

	public static void main(String[] args) {
		System.setProperty("app.key", "ocean-uac");
		SpringApplication.run(OceanApplication.class, args);
	}
}
