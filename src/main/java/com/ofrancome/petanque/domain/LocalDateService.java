package com.ofrancome.petanque.domain;

import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class LocalDateService {

    public LocalDate today() {
        return LocalDate.now();
    }
}
