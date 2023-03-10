package br.springapi.springapi.resources;

import br.springapi.springapi.domain.User;
import br.springapi.springapi.domain.dto.UserDTO;
import br.springapi.springapi.services.UserService;
import br.springapi.springapi.services.impl.UserServiceImpl;
import org.h2.command.dml.MergeUsing;
import org.h2.engine.DbObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.h2.message.Trace.INDEX;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@SpringBootTest
class UserResourceTest {

    private static final Integer ID = 0;
    private static final String NAME = "Joao";
    private static final String EMAIL = "joao@email.com";
    private static final String PASSWORD = "1234";

    private static final Integer INDEX = 0;

    



    @InjectMocks
    private UserResource resource;

    @Mock
    private UserServiceImpl service;

    @Mock
    private ModelMapper mapper;
    private User user = new User();
    private UserDTO userDTO = new UserDTO();

    @BeforeEach
    void setUp() {

        MockitoAnnotations.openMocks(this);
        startUser();

    }

    @Test //GET ID userResource
    void whenFindByIdThenReturnSuccess() {
        when(service.findById(anyInt())).thenReturn(user);
        when(mapper.map(any(), any())).thenReturn(userDTO);

        ResponseEntity<UserDTO> response = resource.findById(ID);

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(UserDTO.class, response.getBody().getClass());

        assertEquals(ID, response.getBody().getId());
        assertEquals(NAME, response.getBody().getName());
        assertEquals(EMAIL, response.getBody().getEmail());
        assertEquals(PASSWORD, response.getBody().getPassword());
    }

    @Test //GET All userResource
    void whenFindAllThenReturnAnListOfUserDto() {
        when(service.findAll()).thenReturn(List.of(user));
        when(mapper.map(any(), any())).thenReturn(userDTO);

        ResponseEntity<List<UserDTO>> response = resource.findAll();

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(ArrayList.class, response.getBody().getClass());
        assertEquals(UserDTO.class, response.getBody().get(INDEX).getClass());

        assertEquals(ID, response.getBody().get(INDEX).getId());
        assertEquals(NAME, response.getBody().get(INDEX).getName());
        assertEquals(EMAIL, response.getBody().get(INDEX).getEmail());
        assertEquals(PASSWORD, response.getBody().get(INDEX).getPassword());


    }

    @Test //Create
    void whenCreateThenReturnCreated() {

        when(service.create(any())).thenReturn(user);

        ResponseEntity<UserDTO> response = resource.create(userDTO);
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getHeaders().get("Location"));


    }

    @Test //UPDATE
    void whenUpdateThenReturnSuccess() {
        when(service.update(userDTO)).thenReturn(user);
        when(mapper.map(any(), any())).thenReturn(userDTO);

        ResponseEntity<UserDTO> response = resource.update(ID, userDTO);

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(UserDTO.class, response.getBody().getClass());


        assertEquals(ID, response.getBody().getId());
        assertEquals(NAME, response.getBody().getName());
        assertEquals(EMAIL, response.getBody().getEmail());

    }

    @Test //Delete
    void whenDeleteThenReturnSuccess() {
        doNothing().when(service).delete(anyInt());

        ResponseEntity<UserDTO> response = resource.delete(ID);
        assertNotNull(response);
        assertEquals(ResponseEntity.class, response.getClass());
        verify(service, times(1)).delete(anyInt());
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());

    }

    private void startUser() {
        user = new User(ID, NAME, EMAIL,  PASSWORD);
        userDTO = new UserDTO(ID, NAME, EMAIL, PASSWORD);

    }
}