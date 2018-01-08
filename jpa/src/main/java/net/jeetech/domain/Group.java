package net.jeetech.domain;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Set;

/**
 * Created by qiuzhanghua on 16/5/11.
 */
@Entity
@Table(name = "GROUPS")
public class Group implements Serializable {
  @Id
  @Pattern(regexp = "[A-Za-z0-9_\\-]+")
  @Column(name = "CODE", nullable = false, length = 50)
  private String code;

  @Column(name = "NAME", nullable = false, length = 100)
  private String name;

  @Column(name = "ENABLED", nullable = false)
  private boolean enabled;

  @ManyToMany
  @JoinTable(name = "USER_GROUP_ROLE", joinColumns = @JoinColumn(name = "GROUP_CODE", referencedColumnName = "CODE"), inverseJoinColumns = @JoinColumn(name = "ROLE_CODE", referencedColumnName = "CODE"), uniqueConstraints = @UniqueConstraint(columnNames = {
      "GROUP_CODE", "ROLE_CODE" }))
  private Set<Role> roles;

//  @ManyToMany(mappedBy="groups")
//  private Set<User> users;

}
