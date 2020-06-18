package com.example.try_on_server.Respository;

import com.example.try_on_server.domain.HomepageInfoDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface HomepageInfoRespository  extends JpaRepository<HomepageInfoDao,Long> {
    @Override
    List<HomepageInfoDao> findAll();

    List<HomepageInfoDao> findByHomepageState(String state);

}
