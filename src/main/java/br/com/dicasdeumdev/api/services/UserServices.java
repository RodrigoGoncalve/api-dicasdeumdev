package br.com.dicasdeumdev.api.services;

import br.com.dicasdeumdev.api.domain.User;

public interface UserServices {

    User findById(Integer id);
}
