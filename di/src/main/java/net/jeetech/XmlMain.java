package net.jeetech;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by qiuzhanghua on 16/4/30.
 */
public class XmlMain {
  private static final Logger log = LoggerFactory.getLogger(XmlMain.class);

  public static void main(String[] args) {
    AbstractApplicationContext context = new ClassPathXmlApplicationContext(
        "/META-INF/applicationContext.xml");
    context.registerShutdownHook();

    DataSource dataSource = (DataSource) context.getBean("dataSource");
    System.out.println(dataSource);
    XmlBean xmlBean = (XmlBean) context.getBean("xmlBean");
    System.out.println(xmlBean);
    log.debug("here : {}", xmlBean);
    try {
      Connection connection = dataSource.getConnection();
      System.out.println(connection);
    } catch (SQLException e) {
      e.printStackTrace();
    }

  }
}
