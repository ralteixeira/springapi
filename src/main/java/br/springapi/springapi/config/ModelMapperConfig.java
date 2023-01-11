package br.springapi.springapi.config;

import org.apache.tomcat.util.modeler.modules.ModelerSource;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper mapper(){
        return new ModelMapper();
    }
}
