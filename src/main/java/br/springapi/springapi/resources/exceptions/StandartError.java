package br.springapi.springapi.resources.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;


@Getter
@AllArgsConstructor
public class StandartError {

    private LocalDateTime timestamp;
    private Integer status;
    private String error;
    private String path;

}
