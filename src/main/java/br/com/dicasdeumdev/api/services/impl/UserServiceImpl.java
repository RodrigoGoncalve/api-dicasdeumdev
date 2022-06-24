package br.com.dicasdeumdev.api.services.impl;

import br.com.dicasdeumdev.api.domain.User;
import br.com.dicasdeumdev.api.repositories.UserRepositoy;
import br.com.dicasdeumdev.api.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserServices {

    @Autowired
    private UserRepositoy userRepositoy;

    @Override
    public User findById(Integer id) {
        Optional<User> obj = userRepositoy.findById(id);
        return obj.orElse(null);
    }
}
