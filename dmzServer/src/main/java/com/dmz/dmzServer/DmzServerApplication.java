package com.dmz.dmzServer;

import com.dmz.dmzServer.bean.ConfigBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({ConfigBean.class})
public class DmzServerApplication {
//	程序运行主入口
	public static void main(String[] args) {
		SpringApplication.run(DmzServerApplication.class, args);
	}
}
