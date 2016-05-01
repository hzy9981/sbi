package net.jeetech;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.zaxxer.hikari.HikariDataSource;

@SpringBootApplication
public class Di2bootApplication {

	@Autowired
	DataSource dataSource;

	@Bean
	public AnnotationBean annotationBean() {
		AnnotationBean annotationBean = new AnnotationBean();
		annotationBean.setP1(99);
		annotationBean.setDataSource(dataSource);
		return annotationBean;
	}

	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext = SpringApplication.run(Di2bootApplication.class, args);
		DataSource dataSource = applicationContext.getBean(DataSource.class);
		System.out.println(applicationContext.getBean(DataSource.class));
		System.out.println(applicationContext.getBean(AnnotationBean.class));
		try {
			Connection connection = dataSource.getConnection();
			ResultSet rs = connection.createStatement().executeQuery("SELECT 1");
			if (rs.first()) {
				System.out.println("Connection OK!");
			} else {
				System.out.println("Something is wrong");
			}
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
