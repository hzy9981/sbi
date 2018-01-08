package net.jeetech.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Email;


/**
 * Created by qiuzhanghua on 16/5/3.
 */
@Entity
@Table(name = "USERS")
public class User implements Serializable {
  private static final long serialVersionUID = 764212103387580482L;

  @Id
  @GeneratedValue(generator = "system-uuid")
  @GenericGenerator(name = "system-uuid", strategy = "uuid")
  @Column(name = "code", nullable = false, length = 32)
  private String code;

  @Column(name = "NAME", nullable = false, length = 50)
  private String name;

  private Integer age;

  @Column(name = "PASSWORD", nullable = false, length = 64)
  private String password;

  @Column(name = "ENABLED", nullable = false)
  private boolean enabled = false;

  @Column(name = "EMAIL", nullable = false, length = 50, unique = true)
  @Email
  private String email;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "USER_ROLE", joinColumns = @JoinColumn(name = "USER_CODE", referencedColumnName = "CODE"), inverseJoinColumns = @JoinColumn(name = "ROLE_CODE", referencedColumnName = "CODE"), uniqueConstraints = @UniqueConstraint(columnNames = {
      "USER_CODE", "ROLE_CODE" }))
  private Set<Role> roles;

  public User(String img, String path) {
	this.name=img;
	this.password=path;
	this.email=path.hashCode()+"@g.cc";//email 不能重复使用了url，同时校验了图片的重复上传
}

public User() {
	// TODO Auto-generated constructor stub
}

public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = DigestUtils.sha1Hex(StringUtils.trimToEmpty(password));
  }

  public boolean isEnabled() {
    return enabled;
  }

  public void setEnabled(boolean enabled) {
    this.enabled = enabled;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = StringUtils.trimToNull(StringUtils.trimToEmpty(email).toLowerCase());
  }
}
