package com.example.demo3.Repository;

import com.example.demo3.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

    User findUserByEmail(String email);
}
