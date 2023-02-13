 package com.example.demo.Repositery;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.Model.Users;

public interface UserRepositery extends CrudRepository<Users, String>{

}
