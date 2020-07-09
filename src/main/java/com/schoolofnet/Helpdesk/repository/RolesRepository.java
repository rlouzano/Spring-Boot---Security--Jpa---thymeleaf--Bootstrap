package com.schoolofnet.Helpdesk.repository;

import com.schoolofnet.Helpdesk.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolesRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);
}
