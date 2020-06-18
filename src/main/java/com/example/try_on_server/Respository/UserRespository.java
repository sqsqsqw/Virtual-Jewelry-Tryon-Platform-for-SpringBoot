package com.example.try_on_server.Respository;

import com.example.try_on_server.domain.UserDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserRespository extends JpaRepository<UserDao,Long> {

    @Override
    List<UserDao> findAll();

    @Override
    UserDao getOne(Long id);// throws EntityNotFoundException;

    List<UserDao> findByUserName(String userName);

    List<UserDao> findByRoleID(Long roleID);
}
