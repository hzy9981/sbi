package net.jeetech;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by qiuzhanghua on 16/5/26.
 */
@Service
public class JpaService {
	@Autowired
	private IdiomUiApplication.Jpas jpas;


	public String defaultIdiom() {
		return "No pain, no gain";
	}

	public String findByCamera(String path) {
		return jpas.findByCamera(path);
	}

	public String addface(String img ,String path) {
		return jpas.addface(img,path);
	}

	public String kv(String  face_token) {
		// TODO Auto-generated method stub
		return jpas.kv(face_token);
	}
}
