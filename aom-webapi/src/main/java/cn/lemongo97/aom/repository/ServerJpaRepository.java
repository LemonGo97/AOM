package cn.lemongo97.aom.repository;

import cn.lemongo97.aom.model.server.ServerPO;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author lemongo97
 */
public interface ServerJpaRepository extends JpaRepository<ServerPO, String> {
}
