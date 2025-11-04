package org.example.config;

import org.example.service.*;
import org.example.dto.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public authorDTO authorDTO() {
        return new authorDTO();
    }

    @Bean
    public publisherDTO publisherDTO() {
        return new publisherDTO();
    }

    @Bean
    public bookDTO bookDTO(authorDTO authorDTO, publisherDTO publisherDTO) {
        return new bookDTO(authorDTO, publisherDTO);
    }

    @Bean
    public LibServiceInterface libraryService(bookDTO bookDTO, authorDTO authorDTO, publisherDTO publisherDTO) {
        return new LibService(bookDTO, authorDTO, publisherDTO);
    }
}