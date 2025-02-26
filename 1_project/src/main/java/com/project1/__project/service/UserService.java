package com.project1.__project.service;

import com.project1.__project.model.User;
import com.project1.__project.model.UserAuth;
import com.project1.__project.repositary.UserAuthRepo;
import com.project1.__project.repositary.Userrepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    Userrepo userrepo;

    @Autowired
    UserAuthRepo repo;

    public List<User> getInfo(){
        return userrepo.findAll();
    }

    public UserAuth register(UserAuth user) {
        return repo.save(user);
    }
}
