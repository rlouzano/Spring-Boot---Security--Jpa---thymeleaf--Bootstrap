package com.schoolofnet.Helpdesk.service;

import com.schoolofnet.Helpdesk.models.Role;
import com.schoolofnet.Helpdesk.models.User;
import com.schoolofnet.Helpdesk.repository.RolesRepository;
import com.schoolofnet.Helpdesk.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

/*    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
*/
    @Autowired
    private RolesRepository rolesRepository;

    public UserServiceImpl(UserRepository userRepository, /*BCryptPasswordEncoder bCryptPasswordEncoder,*/ RolesRepository rolesRepository){
        this.userRepository = userRepository;
  //      this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.rolesRepository = rolesRepository;
    }

    @Override
    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    @Override
    public User create(User user) {
      //  user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));  //Gravar a senha criptografada no banco de dados
        user.setPassword(user.getPassword());
        Role userRole  = this.rolesRepository.findByName("USER");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        return this.userRepository.save(user);
    }


    @Override
    public User show(Long id) {
        return findById(id);
    }


    @Override
    public Boolean delete(Long id) {
        if(findById(id) != null){
            this.userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private User findById(Long id){
        return this.userRepository.getOne(id);
    }

    @Override
    public Boolean update(Long id, User user) {
        User userExists = findById(id);
        if(userExists != null){
            userExists.setId(user.getId());
            userExists.setName(user.getName());
            userExists.setLastname(user.getLastname());
            userExists.setEmail(user.getEmail());
            userExists.setPassword(user.getPassword());
            //userExists.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));
            userExists.setActive(user.getActive());
            this.userRepository.save(user);

            return true;
        }
        return false;
    }


}
