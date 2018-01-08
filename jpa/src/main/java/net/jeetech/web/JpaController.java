package net.jeetech.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.jeetech.domain.User;
import net.jeetech.service.UserService;

import java.util.Random;

/**
 * Created by qiuzhanghua on 16/5/26.
 */
@RestController
public class JpaController {
  public static final String[] IDIOMS = {"齐心协力", "艰苦奋斗", "万紫千红", "走为上"};
  public static Random random = new Random();


  @RequestMapping("/")
  public String idiom() {
    return IDIOMS[random.nextInt(IDIOMS.length)];
  }
  @RequestMapping(value = "/addface", method = RequestMethod.GET)
  public String addface(@RequestParam(value="img",required=false) String img,@RequestParam(value="path",required=false) String path) {
//    return idioms.idiom();
//    return idiomService.idiom();
    userService.persist(new User(img,path));
    return "{}";
  }
  @RequestMapping(value = "/kv", method = RequestMethod.GET)
  public String kv(@RequestParam(value="face_token",required=false) String face_token) {
//    return idioms.idiom();
//    return idiomService.idiom();
    return userService.find(face_token);
  }
  @Autowired
  UserService userService;
}
