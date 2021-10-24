package cn.lemongo97.aom.repository;

import cn.lemongo97.aom.model.application.ApplicationPO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplicationJpaRepository  extends JpaRepository<ApplicationPO, String> {

    Page<ApplicationPO> queryByNameOrderByPackageNameDesc(String name, Pageable pageable);

    List<ApplicationPO> queryByNameOrderByPackageNameDesc(String name);
}
