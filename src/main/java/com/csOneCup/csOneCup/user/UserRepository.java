package com.csOneCup.csOneCup.user;

import com.csOneCup.csOneCup.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
