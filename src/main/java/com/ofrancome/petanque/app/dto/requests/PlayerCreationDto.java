package com.ofrancome.petanque.app.dto.requests;

import jakarta.validation.constraints.NotBlank;

public class PlayerCreationDto {

    @NotBlank
    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
