package org.efrei.demo.services;

import org.efrei.demo.models.Movie;
import org.efrei.demo.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    private final MovieRepository repository;

    @Autowired
    public MovieService(MovieRepository repository) {
        this.repository = repository;
    }

    public List<Movie> findAll() {
        return repository.findAll();
    }

    public Movie findById(String id) {
        return repository.findById(id).orElse(null);
    }

    public void create(Movie movie) {
        repository.save(movie);
    }

    public void update(String id, Movie movie) {
        Movie existingMovie = findById(id);
        if (existingMovie != null) {
            existingMovie.setTitle(movie.getTitle());
            existingMovie.setDuration(movie.getDuration());
            repository.save(existingMovie);
        }
    }

    public void deleteById(String id) {
        repository.deleteById(id);
    }
}
