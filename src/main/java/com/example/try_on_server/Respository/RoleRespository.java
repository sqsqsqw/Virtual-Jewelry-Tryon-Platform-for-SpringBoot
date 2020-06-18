package com.example.try_on_server.Respository;

import com.example.try_on_server.domain.RoleDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface RoleRespository extends JpaRepository<RoleDao, Long> {
    @Override
    List<RoleDao> findAll();
    List<RoleDao> findByRoleID(Long roleID);

    List<RoleDao> findByRoleName(String name);
}
