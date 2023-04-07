 package com.example.demo.Repositery.Auth;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.Model.User.Users;

public interface AuthRepositery extends CrudRepository<Users, String>{

}
