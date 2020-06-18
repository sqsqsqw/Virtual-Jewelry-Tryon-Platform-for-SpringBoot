package com.example.try_on_server.Respository;

import com.example.try_on_server.domain.CommunityCommentDao;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CommunityCommentRespository extends JpaRepository<CommunityCommentDao, Long> {
    @Override
    List<CommunityCommentDao> findAll();

    List<CommunityCommentDao> findByCommunityDistributeID(Long id);

    List<CommunityCommentDao> findByCommunityDistributeID(Long id, Sort sort);
}
