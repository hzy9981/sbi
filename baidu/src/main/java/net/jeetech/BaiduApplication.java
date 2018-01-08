package net.jeetech;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@EnableEurekaClient
//@EnableDiscoveryClient
public class BaiduApplication {

	public static void main(String[] args) {
    ConfigurableApplicationContext context = SpringApplication.run(BaiduApplication.class, args);
    System.out.println(context.getEnvironment().getProperty("info.name"));
  }
}
