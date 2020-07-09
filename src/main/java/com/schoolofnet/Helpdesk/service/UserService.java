package com.schoolofnet.Helpdesk.service;

import com.schoolofnet.Helpdesk.models.Role;
import com.schoolofnet.Helpdesk.models.User;
import com.schoolofnet.Helpdesk.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    public List<User> findAll();

    public User create(User user);

    public Boolean delete(Long id);

    public Boolean update(Long id, User user);

    public User show(Long id);


}
