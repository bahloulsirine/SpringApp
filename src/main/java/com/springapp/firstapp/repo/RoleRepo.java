package com.springapp.firstapp.repo;

import com.springapp.firstapp.module.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepo extends JpaRepository<Role,Long> {
    Role getRoleById(Long id);
}
