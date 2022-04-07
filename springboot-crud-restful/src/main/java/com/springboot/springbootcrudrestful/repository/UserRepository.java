package com.springboot.springbootcrudrestful.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.springbootcrudrestful.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
