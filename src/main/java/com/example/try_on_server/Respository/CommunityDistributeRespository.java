package com.example.try_on_server.Respository;

import com.example.try_on_server.domain.CommunityDistributeDao;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CommunityDistributeRespository extends JpaRepository<CommunityDistributeDao, Long> {

    @Override
    List<CommunityDistributeDao> findAll();

    CommunityDistributeDao getOne(Long a);

    List<CommunityDistributeDao> findByCommunityDistributeID(Long a);

    @Override
    List<CommunityDistributeDao> findAll(Sort sort);
}
