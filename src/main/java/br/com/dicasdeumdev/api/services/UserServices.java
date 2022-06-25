package br.com.dicasdeumdev.api.services;

import br.com.dicasdeumdev.api.domain.User;

import java.util.List;

public interface UserServices {

    User findById(Integer id);
    List<User> findAll();
}
