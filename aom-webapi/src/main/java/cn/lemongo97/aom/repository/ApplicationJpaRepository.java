package cn.lemongo97.aom.repository;

import cn.lemongo97.aom.model.application.ApplicationVersionPO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplicationJpaRepository  extends JpaRepository<ApplicationVersionPO, String> {

    Page<ApplicationVersionPO> queryByNameOrderByPackageNameDesc(String name, Pageable pageable);

    List<ApplicationVersionPO> queryByNameOrderByPackageNameDesc(String name);
}
