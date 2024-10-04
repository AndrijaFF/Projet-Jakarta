package org.efrei.demo.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import java.util.List;

public class CreateSalle {

    @NotEmpty(message = "Le nom est obligatoire")
    private String name;

    @Positive(message = "La capacité doit être un nombre positif")
    private int capacity;

    private List<String> movieIds;

    // Getters et Setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public List<String> getMovieIds() {
        return movieIds;
    }

    public void setMovieIds(List<String> movieIds) {
        this.movieIds = movieIds;
    }
}
