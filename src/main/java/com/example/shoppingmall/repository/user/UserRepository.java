package com.example.shoppingmall.repository.user;

import com.example.shoppingmall.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryCustom{
    boolean existsByUsername(String username);
    boolean existsByTelephone(String telephone);
    boolean existsByEmail(String email);
}