package org.efrei.demo.services;

import org.efrei.demo.dto.CreateSalle;
import org.efrei.demo.models.Movie;
import org.efrei.demo.models.Salle;
import org.efrei.demo.repository.SalleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class SalleService {

    private final SalleRepository repository;
    private final MovieService movieService;

    @Autowired
    public SalleService(SalleRepository repository, MovieService movieService) {
        this.repository = repository;
        this.movieService = movieService;
    }

    public List<Salle> findAll() {
        return repository.findAll();
    }

    public Salle findById(String id) {
        return repository.findById(id).orElse(null);
    }

    public Salle create(CreateSalle createSalle) {
        Salle salle = new Salle();
        salle.setName(createSalle.getName());
        salle.setCapacity(createSalle.getCapacity());

        Set<Movie> movies = new HashSet<>();
        for (String movieId : createSalle.getMovieIds()) {
            Movie movie = movieService.findById(movieId);
            if (movie != null) {
                movies.add(movie);
            } else {
                // Si un film n'existe pas, on lève une exception
                throw new IllegalArgumentException("Film avec l'ID " + movieId + " non trouvé");
            }
        }
        salle.setMovies(movies);
        return repository.save(salle);
    }

    public void update(String id, Salle salle) {
        Salle existingSalle = findById(id);
        if (existingSalle != null) {
            existingSalle.setName(salle.getName());
            existingSalle.setCapacity(salle.getCapacity());
            existingSalle.setMovies(salle.getMovies());
            repository.save(existingSalle);
        }
    }

    public void deleteById(String id) {
        repository.deleteById(id);
    }
}
