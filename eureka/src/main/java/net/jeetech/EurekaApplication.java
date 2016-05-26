package net.jeetech;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@EnableEurekaServer
public class EurekaApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(EurekaApplication.class, args);
		String property = context.getEnvironment().getProperty("info.name");
		System.out.println(property);
	}
}
