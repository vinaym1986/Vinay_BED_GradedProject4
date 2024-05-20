package com.greatlearning.ems.controller;

import com.greatlearning.ems.models.RoleRequest;
import com.greatlearning.ems.security.entity.Role;
import com.greatlearning.ems.security.entity.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/roles")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @PostMapping("/create")
    public ResponseEntity<Role> addRole(@RequestBody RoleRequest roleRequest) {
        Role role = new Role();
        role.setName(roleRequest.getName());

        Role createdRole = roleService.createRole(role);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdRole);
    }
}
