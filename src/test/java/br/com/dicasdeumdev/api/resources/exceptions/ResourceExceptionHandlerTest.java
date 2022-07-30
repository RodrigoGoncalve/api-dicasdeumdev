package br.com.dicasdeumdev.api.resources.exceptions;

import br.com.dicasdeumdev.api.services.excepyions.DataIntergratyViolationException;
import br.com.dicasdeumdev.api.services.excepyions.ObjectNotFoundexception;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ResourceExceptionHandlerTest {

    public static final String OBJETO_NAO_ENCONTRADO = "Objeto não encontrado";
    public static final String E_MAIL_JA_EXISTE_EM_NOSSA_BASE_DE_DADOS = "E-mail já existe em nossa base de dados";
    @InjectMocks
    private ResourceExceptionHandler exceptionHandler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void whenObjectNotFoundexceptionThenReturnAReponseEntity() {
        ResponseEntity<StanderError> response = exceptionHandler
                .objectNoutFound(new ObjectNotFoundexception(OBJETO_NAO_ENCONTRADO),
                        new MockHttpServletRequest());

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(StanderError.class, response.getBody().getClass());
        assertEquals(OBJETO_NAO_ENCONTRADO, response.getBody().getError());
        assertEquals(404, response.getBody().getStatus());
        assertNotEquals("/user/2", response.getBody().getPath());
        assertNotEquals(LocalDateTime.now(), response.getBody().getTimestamp());
    }

    @Test
    void whenDatIntegrityViolationExceptionThenReturnResponseEntity() {
        ResponseEntity<StanderError> response = exceptionHandler
                .datIntegrityViolationException(
                        new DataIntergratyViolationException(E_MAIL_JA_EXISTE_EM_NOSSA_BASE_DE_DADOS),
                        new MockHttpServletRequest()
                );

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(StanderError.class, response.getBody().getClass());
        assertEquals(E_MAIL_JA_EXISTE_EM_NOSSA_BASE_DE_DADOS, response.getBody().getError());
        assertEquals(400, response.getBody().getStatus());
    }
}