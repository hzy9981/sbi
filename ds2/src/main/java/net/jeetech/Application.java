package net.jeetech;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.util.ClassUtils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by qiuzhanghua on 16/5/1.
 */

@SpringBootApplication
public class Application {

  @Primary
  @Bean
  @ConfigurationProperties(prefix = "spring.datasource")
  public DataSource dataSource() {
    try {
      Class<? extends DataSource> clazz = (Class<? extends DataSource>) Class.forName("com.zaxxer.hikari.HikariDataSource");
      return DataSourceBuilder.create().type(clazz).build();
    } catch (ClassNotFoundException e) {
      return DataSourceBuilder.create().build();
    }
  }

  @Bean(name = "second")
  @ConfigurationProperties(prefix = "second.datasource")
  public DataSource secondDataSource() {
    return DataSourceBuilder.create().build();
  }

  public static void main(String[] args) {
    ApplicationContext ctx = SpringApplication.run(Application.class, args);
    DataSource datasource = ctx.getBean(DataSource.class);
    System.out.println(datasource);
    try (Connection connection = datasource.getConnection()) {
      ResultSet rs = connection.createStatement().executeQuery("SELECT 1");
      if (rs.first()) {
        System.out.println("Connection OK!");
      } else {
        System.out.println("Something is wrong");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    DataSource second = (DataSource) ctx.getBean("second");
    System.out.println("Second  DataSource = " + second);

  }
}
