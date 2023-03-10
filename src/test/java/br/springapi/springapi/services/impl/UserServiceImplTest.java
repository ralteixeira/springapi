package br.springapi.springapi.services.impl;

import br.springapi.springapi.domain.User;
import br.springapi.springapi.domain.dto.UserDTO;
import br.springapi.springapi.repositories.UserRepository;
import br.springapi.springapi.services.exceptions.DataIntegratyViolationException;
import br.springapi.springapi.services.exceptions.ObjectNotFoundException;
import lombok.Data;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.validation.beanvalidation.SpringValidatorAdapter;

import java.awt.*;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class UserServiceImplTest {

    public static final Integer ID = 1;
    public static final String NAME = "Joao";
    public static final String EMAIL = "joao@email.com";
    public static final String PASSWORD = "1234";
    public static final String OBJETO_NAO_ENCONTRADO = "Objeto nao encontrado";
    public static final int INDEX = 0;
    @InjectMocks
    private UserServiceImpl service;

    @Mock
    private UserRepository repository;

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
        when(repository.findById(anyInt())).thenReturn(optionalUser);

        User response = service.findById(ID);


        assertNotNull(response);
        assertEquals(User.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NAME, response.getName());
        assertEquals(EMAIL, response.getEmail());

    }

    @Test
        // GetId
    void whenFindByIdThenReturnAnObjectNotFoundException() {
        when(repository.findById(anyInt())).thenThrow(new ObjectNotFoundException(OBJETO_NAO_ENCONTRADO));
        try {
            service.findById(ID);
        } catch (Exception ex) {
            assertEquals(ObjectNotFoundException.class, ex.getClass());
            assertEquals(OBJETO_NAO_ENCONTRADO, ex.getMessage());  //teste assegura que ambos s??o iguais
        }
    }

    @Test
        //GetAll
    void whenFindAllThenReturnAnListOfUsers() {
        when(repository.findAll()).thenReturn(List.of(user));//retorna lista com um usuario dentro

        List<User> response = service.findAll();

      /*Bloco assegura que o objeto n??o ?? nulo, que o tamanho da
      lista ?? um, e que o objeto que vai vir dentro desta lista seja da classe
      tipo User
       */
        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals(User.class, response.get(INDEX).getClass());


        assertEquals(ID, response.get(INDEX).getId());
        assertEquals(NAME, response.get(INDEX).getName());
        assertEquals(EMAIL, response.get(INDEX).getEmail());
        assertEquals(PASSWORD, response.get(INDEX).getPassword());
    }

    @Test
//Create
    void whenCreateThenReturnSuccess() {
        when(repository.save(any())).thenReturn(user);

        User response = service.create(userDTO);

        assertNotNull(response);
        assertEquals(User.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NAME, response.getName());
        assertEquals(EMAIL, response.getEmail());
        assertEquals(PASSWORD, response.getPassword());

    }

    @Test
    void whenCreateThenReturnAnDataIntegrityViolationException() {
        when(repository.findByEmail(anyString())).thenReturn(optionalUser);

        try {
            optionalUser.get().setId(2); //valida a excess??o ID diferente
            service.create(userDTO);
        } catch (Exception ex) {
            assertEquals(DataIntegratyViolationException.class, ex.getClass());
            assertEquals("E-mail j?? cadastrado no sistema", ex.getMessage());
        }

    }


    @Test
//UpDate
    void whenUpDateThenReturnSuccess() {
        when(repository.save(any())).thenReturn(user);

        User response = service.update(userDTO);

        assertNotNull(response);
        assertEquals(User.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NAME, response.getName());
        assertEquals(EMAIL, response.getEmail());
        assertEquals(PASSWORD, response.getPassword());

    }

    @Test
        //Excessao Create
    void whenUpDateThenReturnAnDataIntegrityViolationException() {
        when(repository.findByEmail(anyString())).thenReturn(optionalUser);

        try {
            optionalUser.get().setId(2); //valida a excess??o ID diferente
            service.create(userDTO);
        } catch (Exception ex) {
            assertEquals(DataIntegratyViolationException.class, ex.getClass());
            assertEquals("E-mail j?? cadastrado no sistema", ex.getMessage());
        }
    }

    @Test
    void deleteWithSuccess() {
        when(repository.findById(anyInt())).thenReturn(optionalUser);
        doNothing().when(repository).deleteById(anyInt());
        service.delete(ID);
        verify(repository, times(1)).deleteById(anyInt());
    }

    @Test
    void whenDeleteThenReturnObjectNotFoundException() {
        when(repository.findById(anyInt()))
                .thenThrow(new ObjectNotFoundException(OBJETO_NAO_ENCONTRADO));
        try {
            service.delete(ID);
        } catch (Exception ex) {
            assertEquals(ObjectNotFoundException.class, ex.getClass());
            assertEquals(OBJETO_NAO_ENCONTRADO, ex.getMessage());
        }
    }

    private void startUser() {
        user = new User(ID, NAME, EMAIL,  PASSWORD);
        userDTO = new UserDTO(ID, NAME, EMAIL, PASSWORD);
        optionalUser = Optional.of(new User(ID, NAME, EMAIL, PASSWORD));
    }
}