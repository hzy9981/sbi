package net.jeetech;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HelloApplication {

  @Bean
  public CommandLineRunner runner() {
    return new CommandLineRunner() {
      @Override
      public void run(String... args) throws Exception {
        System.out.println("Hello, Spring Boot!");
      }
    };
  }

  public static void main(String[] args) {
    SpringApplication.run(HelloApplication.class, args);
  }
}
