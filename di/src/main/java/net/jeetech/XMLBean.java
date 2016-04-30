package net.jeetech;

import javax.inject.Inject;
import javax.sql.DataSource;
import java.io.Serializable;

/**
 * Created by qiuzhanghua on 16/4/30.
 */
public class XmlBean implements Serializable {

  private static final long serialVersionUID = -2457983833429181967L;

  private Integer p1;
  private String p2;

  //	@Autowired
  @Inject
  private DataSource dataSource;

  public Integer getP1() {
    return p1;
  }

  public void setP1(Integer p1) {
    this.p1 = p1;
  }

  public String getP2() {
    return p2;
  }

  public void setP2(String p2) {
    this.p2 = p2;
  }

  public DataSource getDataSource() {
    return dataSource;
  }

  public void setDataSource(DataSource dataSource) {
    this.dataSource = dataSource;
  }

  @Override
  public String toString() {
    return "XmlBean [p1=" + p1 + ", p2=" + p2 + ", dataSource=" + dataSource
        + "]";
  }

}
