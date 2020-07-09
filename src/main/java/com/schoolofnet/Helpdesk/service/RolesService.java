package com.schoolofnet.Helpdesk.service;

import com.schoolofnet.Helpdesk.models.Role;

import java.util.List;

public interface RolesService {

    public List<Role> findAll();

    public Role create(Role role);

    public Boolean delete(Long id);
}
