package br.springapi.springapi.resources.exceptions;


import br.springapi.springapi.services.exceptions.ObjectNotFoundException;
import com.sun.net.httpserver.HttpServer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@ControllerAdvice
public class ResourceExceptionHandler {


    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandartError>objectNotFound(ObjectNotFoundException ex, HttpServletRequest request){

        StandartError error = new StandartError(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(), ex.getMessage(), request.getRequestURI());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);

    }
}
