package net.jeetech;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@EnableConfigServer
public class ConfigServerApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(ConfigServerApplication.class, args);
		String name = context.getEnvironment().getProperty("spring.application.name");
		System.out.println(name);
		System.out.println(context.getEnvironment().getProperty("info.name"));
	}
}
