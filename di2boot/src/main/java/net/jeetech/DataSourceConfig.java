package net.jeetech;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by qiuzhanghua on 16/4/30.
 */
@Configuration
@ComponentScan
public class DataSourceConfig {
  @Value("${spring.datasource.username}")
  private String username;

  @Value("${spring.datasource.password}")
  private String password;

  @Value("${spring.datasource.url}")
  private String dataSourceUrl;

  @Value("${spring.datasource.driverClassName}")
  private String driverClassName;

  @Value("${spring.datasource.poolName}")
  private String poolName;

  @Value("${spring.datasource.connectionTimeout}")
  private int connectionTimeout;

  @Value("${spring.datasource.maxLifetime}")
  private int maxLifetime;

  @Value("${spring.datasource.maximumPoolSize}")
  private int maximumPoolSize;

  @Value("${spring.datasource.minimumIdle}")
  private int minimumIdle;

  @Value("${spring.datasource.idleTimeout}")
  private int idleTimeout;

  @Bean
  public DataSource primaryDataSource() {
    Properties dsProps = new Properties();
    dsProps.put("url", dataSourceUrl);
    dsProps.put("user", username);
    dsProps.put("password", password);
    dsProps.put("prepStmtCacheSize",250);
    dsProps.put("prepStmtCacheSqlLimit",2048);
    dsProps.put("cachePrepStmts",Boolean.TRUE);
    dsProps.put("useServerPrepStmts",Boolean.TRUE);

    Properties configProps = new Properties();
    configProps.put("jdbcUrl", dataSourceUrl);
    configProps.put("driverClassName", driverClassName);
    configProps.put("poolName",poolName);
    configProps.put("maximumPoolSize",maximumPoolSize);
    configProps.put("minimumIdle",minimumIdle);
    configProps.put("minimumIdle",minimumIdle);
    configProps.put("connectionTimeout", connectionTimeout);
    configProps.put("idleTimeout", idleTimeout);
    configProps.put("dataSourceProperties", dsProps);

    HikariConfig hc = new HikariConfig(configProps);
    HikariDataSource ds = new HikariDataSource(hc);
    return ds;
  }

}
