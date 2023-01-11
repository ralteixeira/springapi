package br.springapi.springapi.services;

import br.springapi.springapi.domain.User;

import java.util.List;

public interface UserService {

    User findById(Integer id);
    List<User> findAll();


}
