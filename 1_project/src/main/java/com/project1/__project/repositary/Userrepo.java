package com.project1.__project.repositary;

import com.project1.__project.model.User;
import com.project1.__project.model.UserAuth;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Userrepo extends JpaRepository<User, Integer> {
}
