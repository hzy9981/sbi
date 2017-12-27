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
  private IdiomService idiomService;

  @RequestMapping("/choose")
  public ServiceInstance choose() {
    return lb.choose("Idioms");

  }

  @RequestMapping(value = "/", produces = "application/json; charset=UTF-8")
  public String home() {
//    return idioms.idiom();
//    return idiomService.idiom();
    return idiomUiPropeties().getPrefix() + idiomService.idiom();
  }

  @RequestMapping(value = "/camera/{path}",method = RequestMethod.GET)
  public String findByCamera(@RequestParam(value="path",required=false) String path) {
//    return idioms.idiom();
//    return idiomService.idiom();
    return idiomService.findByCamera(path);
  }

  @FeignClient("idioms")
  public interface Idioms {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String idiom();
    @RequestMapping(method = RequestMethod.GET, value = "/camera/{path}")
    public String findByCamera(@RequestParam(value="path",required=false) String path);
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
