package net.jeetech.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

/**
 * Created by qiuzhanghua on 16/5/26.
 */
@RestController
public class IdiomController {
  public static final String[] IDIOMS = {"齐心协力", "艰苦奋斗", "万紫千红", "走为上"};
  public static Random random = new Random();

  @RequestMapping("/")
  public String idiom() {
    return IDIOMS[random.nextInt(IDIOMS.length)];
  }

}
