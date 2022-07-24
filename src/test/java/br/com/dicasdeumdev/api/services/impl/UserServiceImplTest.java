package br.com.dicasdeumdev.api.services.impl;

import br.com.dicasdeumdev.api.domain.User;
import br.com.dicasdeumdev.api.domain.dto.UserDTO;
import br.com.dicasdeumdev.api.repositories.UserRepositoy;
import br.com.dicasdeumdev.api.services.excepyions.ObjectNotFoundexception;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserServiceImplTest {

    public static final Integer ID       = 1;
    public static final String NAME      = "Valdir";
    public static final String EMAIL     = "valdir@mail.com";
    public static final String PASSWORD  = "123";
    public static final String OBJETO_NÃO_ENCONTRADO = "Objeto não encontrado";
    public static final int INDEX = 0;
    @InjectMocks
    private UserServiceImpl service;

    @Mock
    private UserRepositoy repositoy;

    @Mock
    private ModelMapper mapper;

    private User user;

    private UserDTO userDTO;

    private Optional<User> optionalUser;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startUser();
    }

    @Test
    void whenFindByIdThenReturnAnUserInstance() {
        when(repositoy.findById(anyInt())).thenReturn(optionalUser);

        User response = service.findById(ID);

        assertNotNull(response);
        assertEquals(User.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NAME, response.getName());
        assertEquals(EMAIL, response.getEmail());
    }

    @Test
    void wheFindByIdThenReturnAnObjectNotFoundException(){
        when(repositoy.findById(anyInt())).thenThrow(new ObjectNotFoundexception(OBJETO_NÃO_ENCONTRADO));

        try{
          service.findById(ID);
        }catch (Exception ex){
            assertEquals(ObjectNotFoundexception.class, ex.getClass());
            assertEquals(OBJETO_NÃO_ENCONTRADO, ex.getMessage());
        }
    }


    @Test
    void whenFindAllThenReturnAnListOfUsers() {
        when(repositoy.findAll()).thenReturn(List.of(user));

        List<User> response = service.findAll();

        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals(User.class, response.get(INDEX).getClass());
        assertEquals(ID, response.get(INDEX).getId());
        assertEquals(NAME, response.get(INDEX).getName());
        assertEquals(EMAIL, response.get(INDEX).getEmail());
        assertEquals(PASSWORD, response.get(INDEX).getPassword());
    }

    @Test
    void whenCreateThenreturnSuccesso() {
        when(repositoy.save(any())).thenReturn(user);

        User user = service.create(userDTO);

        assertNotNull(user);
        assertEquals(User.class,user.getClass());
        assertEquals(ID, user.getId());
        assertEquals(NAME, user.getName());
        assertEquals(EMAIL, user.getEmail());
        assertEquals(PASSWORD, user.getPassword());
        
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    private void startUser(){
        user = new User(ID, NAME, EMAIL, PASSWORD);
        userDTO = new UserDTO(ID, NAME, EMAIL, PASSWORD);
        optionalUser = Optional.of(new User(ID, NAME, EMAIL, PASSWORD));
    }
}