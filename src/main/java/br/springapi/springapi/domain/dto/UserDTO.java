package br.springapi.springapi.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {


    private Integer id;
    private String name;
    private String email;

    @JsonIgnore
    private String password;
}
