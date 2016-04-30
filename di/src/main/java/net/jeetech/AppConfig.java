package net.jeetech;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.sql.DataSource;

/**
 * Created by qiuzhanghua on 16/4/30.
 */
@Configuration
public class AppConfig {

  @Bean
  public AnnotationBean annotationBean() {
    AnnotationBean annotationBean = new AnnotationBean();
    annotationBean.setP1(99);
    annotationBean.setDataSource(dataSource());
    return annotationBean;
  }

  @Bean(destroyMethod = "close")
  public DataSource dataSource() {
    final HikariDataSource ds = new HikariDataSource();
    ds.setDriverClassName("org.mariadb.jdbc.Driver");
    ds.setJdbcUrl("jdbc:mysql://localhost:3306/app?useUnicode=true&characterEncoding=utf-8");
    ds.setUsername("app");
    ds.setPassword("app");

    ds.setConnectionTimeout(3000);
    ds.setMaximumPoolSize(100);
    ds.setConnectionTestQuery("select 1");

    ds.addDataSourceProperty("cachePrepStmts", true);
    ds.addDataSourceProperty("prepStmtCacheSize", 250);
    ds.addDataSourceProperty("prepStmtCacheSqlLimit", 2048);
    ds.addDataSourceProperty("useServerPrepStmts", true);

    return ds;
  }

}
