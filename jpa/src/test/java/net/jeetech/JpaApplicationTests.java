package net.jeetech;

import net.jeetech.JpaApplication;
import net.jeetech.domain.User;
import net.jeetech.service.UserService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;

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
		user.setName("spring.jpa.hibernate.ddl-auto=create-drop");
		user.setAge(45);
<<<<<<< HEAD
		user.setEmail("qiuzhanghua7@icloud.com");
		user.setPassword("a4ea7b3e1155ff137d603e415c0425d9");
//	    userService.persist(user);
	    userService.find("a4ea7b3e1155ff137d603e415c0425d9");
=======
//		user.setEmail("qiuzhanghua5@icloud.com");
		user.setPassword("password");
    userService.persist(user);
>>>>>>> refs/remotes/origin/master
	}

}
