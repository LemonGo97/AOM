package cn.lemongo97.aom.repository;

import cn.lemongo97.aom.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author lemongo97
 */
public interface UserJpaRepository extends JpaRepository<User,Long> {
    /**
     * 通过用户名查找用户
     * @param username
     * @return
     */
    User findUserByUsername(String username);
}