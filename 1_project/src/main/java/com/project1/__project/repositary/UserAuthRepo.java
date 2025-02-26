package com.project1.__project.repositary;

import com.project1.__project.model.UserAuth;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAuthRepo extends JpaRepository<UserAuth,Integer> {
     UserAuth findByUsername(String username);
}
