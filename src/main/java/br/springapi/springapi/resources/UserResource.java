package br.springapi.springapi.resources;


import br.springapi.springapi.domain.User;
import br.springapi.springapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/user")
public class UserResource {

    @Autowired
    private UserService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<User> findById(@PathVariable Integer id){

        return ResponseEntity.ok().body(service.findById(id));
    }
}
