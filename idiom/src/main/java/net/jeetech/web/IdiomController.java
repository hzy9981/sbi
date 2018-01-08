package net.jeetech.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.jeetech.service.FaceTest;
import net.jeetech.service.YingClient;

import java.util.Random;

/**
 * Created by qiuzhanghua on 16/5/26.
 */
@RestController
public class IdiomController {
  public static final String[] IDIOMS = {"齐心协力", "艰苦奋斗", "万紫千红", "走为上"};
  public static Random random = new Random();
  @RequestMapping(value = "/camera/{path}", method = RequestMethod.GET)
  public String findByCamera(@RequestParam(value="path",required=false) String path) {
//    return idioms.idiom();
//    return idiomService.idiom();
    return faceService.findByCamera(path);
  }
  @RequestMapping(value = "/ocr", method = RequestMethod.GET)
  public String ocr(@RequestParam(value="path",required=false) String path) {
//    return idioms.idiom();
//    return idiomService.idiom();
    return ocrService.ocr(path,null);
  }
<<<<<<< HEAD
  @RequestMapping(method = RequestMethod.GET, value = "/detect")
  public String detect(@RequestParam(value="path",required=false) String path) {
	  return faceService.detect(path);
  };
=======
>>>>>>> refs/remotes/origin/master
  
  @Autowired
  FaceTest faceService;
  @Autowired
  YingClient ocrService;
  @RequestMapping("/")
  public String idiom() {
    return IDIOMS[random.nextInt(IDIOMS.length)];
  }

}
