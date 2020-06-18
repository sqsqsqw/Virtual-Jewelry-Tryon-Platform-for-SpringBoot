package com.example.try_on_server.Respository;

import com.example.try_on_server.domain.GoodsCommentDao;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface GoodsCommentRespository extends JpaRepository<GoodsCommentDao, Long> {
    @Override
    List<GoodsCommentDao> findAll();

    List<GoodsCommentDao> findByGoodID(Long goodID);
}
