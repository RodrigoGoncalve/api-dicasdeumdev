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

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ResourceExceptionHandlerTest {

    public static final String OBJETO_NAO_ENCONTRADO = "Objeto não encontrado";
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
    }

    @Test
    void whenDatIntegratyViolationExceptionThenReturnResponseEntity() {
        ResponseEntity<StanderError> response = exceptionHandler
                .datIntegratyViolationException(
                        new DataIntergratyViolationException("E-mail já existe em nossa base de dados"),
                        new MockHttpServletRequest()
                );

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(StanderError.class, response.getBody().getClass());
        assertEquals("E-mail já existe em nossa base de dados", response.getBody().getError());
        assertEquals(400, response.getBody().getStatus());

    }
}