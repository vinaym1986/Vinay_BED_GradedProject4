package com.greatlearning.ems.security.entity.repository;

import com.greatlearning.ems.security.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {
}
