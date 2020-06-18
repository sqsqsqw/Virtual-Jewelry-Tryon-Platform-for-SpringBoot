package com.example.try_on_server.Respository;

import com.example.try_on_server.domain.GoodsInfoDao;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface GoodsInfoRespository extends JpaRepository<GoodsInfoDao, Long> {

    @Override
    List<GoodsInfoDao> findAll();

    @Override
    List<GoodsInfoDao> findAll(Sort sort);

    List<GoodsInfoDao> findByGoodClassID(Long id, Sort sort);

    @Override
    GoodsInfoDao getOne(Long id);

    List<GoodsInfoDao> findByGoodID(Long id);

    List<GoodsInfoDao> findBySellerId(Long id);

    List<GoodsInfoDao> findByGoodDescriptionLike(String str);
}
