package com.example.try_on_server.Respository;

import com.example.try_on_server.domain.UserCollectDao;
import com.example.try_on_server.domain.UserDao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserCollectRespository extends JpaRepository<UserCollectDao, Long> {

    @Override
    List<UserCollectDao> findAll();

    @Override
    UserCollectDao getOne(Long aLong);

    List<UserCollectDao> findByUserId(Long userId);

    List<UserCollectDao> findByUserIdAndGoodID(Long userID, Long goodID);


}
