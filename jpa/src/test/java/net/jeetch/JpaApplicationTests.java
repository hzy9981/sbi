package net.jeetch;

import net.jeetch.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;

import net.jeetch.domain.User;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = JpaApplication.class)
public class JpaApplicationTests {
	@Inject
	EntityManagerFactory entityManagerFactory;

  @Inject
  UserService userService;

	@Test
	public void contextLoads() {
		System.out.println(entityManagerFactory);
		User user = new User();
		user.setName("邱张华");
		user.setAge(45);
		user.setEmail("qiuzhanghua@icloud.com");
		user.setPassword("password");
    userService.persist(user);
	}

}
