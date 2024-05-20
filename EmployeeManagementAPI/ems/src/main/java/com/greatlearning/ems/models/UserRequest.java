package com.greatlearning.ems.models;

import com.greatlearning.ems.security.entity.Role;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserRequest {
    private String username;
    private String password;
    private List<Role> roles = new ArrayList<>();
}
