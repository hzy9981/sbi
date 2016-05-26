package net.jeetech;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by qiuzhanghua on 16/5/26.
 */
@ConfigurationProperties(prefix = "idiomui")
public class IdiomUiProperties {
  private String prefix;

  public String getPrefix() {
    return prefix;
  }

  public void setPrefix(String prefix) {
    this.prefix = prefix;
  }
}
