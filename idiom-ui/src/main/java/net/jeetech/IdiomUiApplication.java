package net.jeetech;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

@SpringBootApplication
@EnableEurekaClient
@EnableCircuitBreaker
@EnableFeignClients
@RestController
@EnableConfigurationProperties
public class IdiomUiApplication {

	@Autowired
	private LoadBalancerClient lb;

	@Autowired
	private JpaService jpaService;

	@Autowired
	private IdiomService idiomService;

	@RequestMapping("/choose")
	public ServiceInstance choose() {
		return lb.choose("Idioms");

	}

	@RequestMapping(value = "/", produces = "application/json; charset=UTF-8")
	public String home() {
		// return idioms.idiom();
		// return idiomService.idiom();
		return idiomUiPropeties().getPrefix() + idiomService.idiom();
	}

	@RequestMapping(value = "/camera/{path}", method = RequestMethod.GET)
	public String findByCamera(@RequestParam(value = "path", required = false) String path) {
		// return idioms.idiom();
		// return idiomService.idiom();
		String str = idiomService.findByCamera(path);
		// String str = faceService.findByCamera(path);
		JSONObject parse = JSONObject.parseObject(str);
		// parse.putIfAbsent(key, value)
		JSONArray jsonArray = parse.getJSONArray("faces");
		if (jsonArray != null)
			for (int i = 0; i < jsonArray.size(); i++) {
				String uri = jpaService.kv(jsonArray.getJSONObject(i).getString("face_token"));
				jsonArray.getJSONObject(i).replace("face_token", uri);
			}
		return jsonArray == null ? "{}" : jsonArray.toJSONString();

	}

	@RequestMapping(value = "/detect", method = RequestMethod.GET)
	public String detect(@RequestParam(value = "path", required = false) String path) {
		String str = idiomService.detect(path);
		JSONObject parse = JSONObject.parseObject(str);
		jpaService.addface(parse.getString("face_tokens"), path);

		return str;

	}

	@FeignClient("idioms")
	public interface Idioms {
		@RequestMapping(value = "/", method = RequestMethod.GET)
		public String idiom();

		@RequestMapping(method = RequestMethod.GET, value = "/camera/123")
		public String findByCamera(@RequestParam(value = "path", required = false) String path);

		@RequestMapping(method = RequestMethod.GET, value = "/detect")
		public String detect(@RequestParam(value = "path", required = false) String path);
	}

	@FeignClient("jpas")
	public interface Jpas {
		@RequestMapping(value = "/", method = RequestMethod.GET)
		public String idiom();

		@RequestMapping(method = RequestMethod.GET, value = "/camera/123")
		public String findByCamera(@RequestParam(value = "path", required = false) String path);
		// public String findByCamera(String path);

		@RequestMapping(method = RequestMethod.GET, value = "/addface")
		public String addface(@RequestParam(value = "img", required = false) String img,
				@RequestParam(value = "path", required = false) String path);

		@RequestMapping(method = RequestMethod.GET, value = "/kv")
		public String kv(@RequestParam(value = "face_token", required = false) String face_token);
	}

	@Bean
	public IdiomUiProperties idiomUiPropeties() {
		return new IdiomUiProperties();

	}

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(IdiomUiApplication.class, args);
		System.out.println(context.getEnvironment().getProperty("info.name"));

	}
}
