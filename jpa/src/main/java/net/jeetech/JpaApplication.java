package net.jeetech;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.ConfigurableApplicationContext;

import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class JpaApplication {

	public static void main(String[] args) {
	    ConfigurableApplicationContext context = SpringApplication.run(JpaApplication.class, args);
	    System.out.println(context.getEnvironment().getProperty("info.name"));
//		SpringApplication.run(JpaApplication.class, args);
	}
}
