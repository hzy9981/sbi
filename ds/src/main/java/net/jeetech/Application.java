package net.jeetech;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by qiuzhanghua on 16/5/1.
 */
@SpringBootApplication
public class Application {
  public static void main(String[] args) {
    ApplicationContext ctx = SpringApplication.run(Application.class, args);

    DataSource datasource = ctx.getBean(DataSource.class);
    System.out.println(datasource);
    try {
      Connection connection = datasource.getConnection();
      ResultSet rs = connection.createStatement().executeQuery("SELECT 1");
      if (rs.first()) {
        System.out.println("Connection OK!");
      } else {
        System.out.println("Something is wrong");
      }
      connection.close();
      System.exit(0);
    } catch (SQLException e) {
      System.err.println("FAILED!");
      e.printStackTrace();
      System.exit(-2);
    }

  }
}
