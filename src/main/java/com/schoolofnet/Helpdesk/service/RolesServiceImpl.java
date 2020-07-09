package com.schoolofnet.Helpdesk.service;

import com.schoolofnet.Helpdesk.models.Role;
import com.schoolofnet.Helpdesk.repository.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolesServiceImpl implements RolesService {

    @Autowired
    private RolesRepository rolesRepository;

    public RolesServiceImpl(RolesRepository rolesRepository){
        this.rolesRepository = rolesRepository;
    }

    @Override
    public List<Role> findAll() {
        return this.rolesRepository.findAll();
    }

    @Override
    public Role create(Role role) {
        role.setName(role.getName().toUpperCase());  // Para que cadastre sempre com letras MAIUSCULAS
        Role roleCreated =this.rolesRepository.save(role);
        return roleCreated;
    }

    @Override
    public Boolean delete(Long id) {
        if(findById(id) != null){
            this.rolesRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private Role findById(Long id){
        return this.rolesRepository.getOne(id);
    }
}
