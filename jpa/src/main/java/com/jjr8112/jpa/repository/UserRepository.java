package com.jjr8112.jpa.repository;

import com.jjr8112.jpa.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User,Long> {

    List<User> findByName(String name);

    User findById(Integer id);
}
