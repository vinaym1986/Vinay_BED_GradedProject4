package com.greatlearning.ems.security.entity.service;

import com.greatlearning.ems.models.UserRequest;
import com.greatlearning.ems.security.entity.Role;
import com.greatlearning.ems.security.entity.User;
import com.greatlearning.ems.security.entity.repository.RoleRepository;
import com.greatlearning.ems.security.entity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService  implements  IUserService{

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Transactional
    public User createUserWithRole(UserRequest userRequest) {
        User user = new User();
        user.setUsername(userRequest.getUsername());
        user.setPassword(userRequest.getPassword());

        Role role = roleRepository.findById(Long.valueOf(userRequest.getRoles().get(0).getId()))
                .orElseThrow(() -> new RuntimeException("Role not found")); // Handle appropriately
        user.getRoles().add(role);
        userRepository.save(user);
        return user;
    }
}
