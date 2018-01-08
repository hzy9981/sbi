package net.jeetech.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.jeetech.domain.User;

<<<<<<< HEAD
import java.util.Iterator;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by qiuzhanghua on 16/5/3.
 */
@Service
public class UserService {
	private static final Logger log = LoggerFactory.getLogger(UserService.class);

	@PersistenceContext
	EntityManager entityManager;

	@Transactional
	public void persist(User user) {
		entityManager.persist(user);
	}

public String find(String face_token) {
	Iterator iterator = entityManager.createNativeQuery("select u.password from users u"
			+ " where u.name like'%"+face_token+"%'").getResultList().iterator();
	String o = null;
	if(iterator.hasNext())
		o = iterator.next().toString();
=======
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by qiuzhanghua on 16/5/3.
 */
@Service
public class UserService {
	private static final Logger log = LoggerFactory.getLogger(UserService.class);

	@PersistenceContext
	EntityManager entityManager;

	@Transactional
	public void persist(User user) {
		entityManager.persist(user);
	}

public String find(String face_token) {
	String o = (String)entityManager.createQuery("select u.password from User u"
			+ " where u.name like'%"+face_token+"%'").getSingleResult();
>>>>>>> refs/remotes/origin/master

	return o;
	
}

}
