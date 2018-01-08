package net.jeetech.domain;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Set;

/**
 * Created by qiuzhanghua on 16/5/11.
 */
@Entity
@Table(name = "ROLES")
public class Role implements Serializable {
  @Id
  @Length(min = 2)
  @Pattern(regexp = "[A-Za-z0-9_\\-]+")
  @Column(name = "code", nullable = false, length = 50)
  private String code;

  @NotEmpty
  @Length(min = 2)
  @Column(name = "NAME", nullable = false, length = 50)
  private String name;

  @ManyToMany(mappedBy = "roles")
  private Set<User> users;


}
