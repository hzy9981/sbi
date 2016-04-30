package net.jeetech;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by qiuzhanghua on 16/4/30.
 */
public class AnnotationMain {
  public static void main(String[] args) {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
    context.scan("net.jeetech");
    context.refresh();
    context.registerShutdownHook();
    AnnotationBean annotationBean = (AnnotationBean) context.getBean(AnnotationBean.class);
    System.out.println(annotationBean);
    DataSource dataSource = context.getBean(DataSource.class);
    System.out.println(dataSource);
    try {
      Connection connection = dataSource.getConnection();
      System.out.println(connection);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
