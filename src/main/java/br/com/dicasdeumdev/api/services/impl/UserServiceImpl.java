package br.com.dicasdeumdev.api.services.impl;

import br.com.dicasdeumdev.api.domain.User;
import br.com.dicasdeumdev.api.domain.dto.UserDTO;
import br.com.dicasdeumdev.api.repositories.UserRepositoy;
import br.com.dicasdeumdev.api.services.UserServices;
import br.com.dicasdeumdev.api.services.excepyions.DataIntergratyViolationException;
import br.com.dicasdeumdev.api.services.excepyions.ObjectNotFoundexception;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserServices {

    @Autowired
    private UserRepositoy userRepositoy;

    @Autowired
    private ModelMapper mapper;

    @Override
    public User findById(Integer id) {
        Optional<User> obj = userRepositoy.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundexception("Objeto não encontrado"));
    }

    public List<User> findAll(){
        return userRepositoy.findAll();
    }

    @Override
    public User create(UserDTO obj) {
        findByEmail(obj);
        return userRepositoy.save(mapper.map(obj, User.class));
    }

    @Override
    public User update(UserDTO obj) {
        findByEmail(obj);
        return userRepositoy.save(mapper.map(obj, User.class));
    }

    private void findByEmail(UserDTO obj){
       Optional<User> user =  userRepositoy.findByEmail(obj.getEmail());
       if(user.isPresent() && !user.get().getId().equals(obj.getId())){
           throw new DataIntergratyViolationException("E-mail já existe em nossa base de dados");
       }
    }
}
