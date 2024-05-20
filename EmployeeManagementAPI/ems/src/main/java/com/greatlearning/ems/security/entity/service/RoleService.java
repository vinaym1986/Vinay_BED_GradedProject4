package com.greatlearning.ems.security.entity.service;

import com.greatlearning.ems.security.entity.Role;
import com.greatlearning.ems.security.entity.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService  implements   IRoleService{

    @Autowired
    RoleRepository roleRepository;

    @Override
    public Role createRole(Role role) {
        return roleRepository.save(role);
    }
}
