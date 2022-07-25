package br.com.dicasdeumdev.api.resources;

import br.com.dicasdeumdev.api.domain.User;
import br.com.dicasdeumdev.api.domain.dto.UserDTO;
import br.com.dicasdeumdev.api.services.UserServices;
import br.com.dicasdeumdev.api.services.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.MockitoAnnotations.openMocks;

@SpringBootTest
class UserResourceTest {

    public static final Integer ID       = 1;
    public static final String NAME      = "Valdir";
    public static final String EMAIL     = "valdir@mail.com";
    public static final String PASSWORD  = "123";
    public static final String OBJETO_NÃO_ENCONTRADO = "Objeto não encontrado";
    public static final int INDEX = 0;
    public static final String E_MAIL_JA_EXISTE_EM_NOSSA_BASE_DE_DADOS = "E-mail já existe em nossa base de dados";

    @InjectMocks
    private UserResource resource;

    @Mock
    private ModelMapper mapper;

    @Mock
    private UserServiceImpl userService;

    private User user;

    private UserDTO userDTO;


    @BeforeEach
    void setUp() {
        openMocks(this);
        startUser();
    }

    @Test
    void findById() {
    }

    @Test
    void findAll() {
    }

    @Test
    void create() {
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
    }
}