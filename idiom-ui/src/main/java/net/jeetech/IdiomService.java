package net.jeetech;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by qiuzhanghua on 16/5/26.
 */
@Service
public class IdiomService {
	@Autowired
	private IdiomUiApplication.Idioms idioms;

	@HystrixCommand(fallbackMethod = "defaultIdiom")
	public String idiom() {
		return idioms.idiom();
	}

	public String defaultIdiom() {
		return "No pain, no gain";
	}

	public String findByCamera(String path) {
		return idioms.findByCamera(path);
	}
}
