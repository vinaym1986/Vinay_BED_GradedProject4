package com.greatlearning.ems.security.entity.service;

import com.greatlearning.ems.models.UserRequest;
import com.greatlearning.ems.security.entity.User;
import org.springframework.transaction.annotation.Transactional;

public interface IUserService {
    @Transactional
    User createUserWithRole(UserRequest userRequest);
}
