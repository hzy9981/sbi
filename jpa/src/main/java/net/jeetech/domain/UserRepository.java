package net.jeetech.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by qiuzhanghua on 16/5/3.
 */
public interface UserRepository extends
    JpaRepository<User, String>, JpaSpecificationExecutor<User> {

}
