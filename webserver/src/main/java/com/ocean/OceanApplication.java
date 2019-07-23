package com.ocean;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

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
