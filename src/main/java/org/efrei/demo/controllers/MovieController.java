package org.efrei.demo.controllers;

import org.efrei.demo.dto.CreateMovie;
import org.efrei.demo.models.Movie;
import org.efrei.demo.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    private final MovieService service;

    @Autowired
    public MovieController(MovieService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Movie>> findAll() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movie> findById(@PathVariable String id) {
        Movie movie = service.findById(id);
        if (movie == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(movie, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody CreateMovie createMovie) {
        // Convertir CreateMovie en Movie
        Movie movie = new Movie();
        movie.setTitle(createMovie.getTitle());
        movie.setDuration(createMovie.getDuration());

        service.create(movie); // Appeler la méthode de création dans le service
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable String id, @RequestBody Movie movie) {
        service.update(id, movie);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable String id) {
        service.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
