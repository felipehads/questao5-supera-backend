package br.com.banco.bean;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class Beans {

    @Bean
    public ModelMapper getModelMapper() {
        return new ModelMapper();
    }
}
