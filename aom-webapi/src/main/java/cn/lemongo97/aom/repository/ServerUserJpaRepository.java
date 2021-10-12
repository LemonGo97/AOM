package cn.lemongo97.aom.repository;

import cn.lemongo97.aom.model.server.ServerUserPO;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author lemongo97
 */
public interface ServerUserJpaRepository extends JpaRepository<ServerUserPO, String> {
}
