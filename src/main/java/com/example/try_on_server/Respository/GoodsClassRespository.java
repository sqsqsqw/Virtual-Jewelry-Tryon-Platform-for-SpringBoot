package com.example.try_on_server.Respository;

import com.example.try_on_server.domain.GoodsClassDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface GoodsClassRespository extends JpaRepository<GoodsClassDao, Long> {
    @Override
    List<GoodsClassDao> findAll();

    List<GoodsClassDao> findByClassName(String className);
}
