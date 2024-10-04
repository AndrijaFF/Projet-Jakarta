package org.efrei.demo.services;
import org.efrei.demo.dto.CreateActor;
import org.efrei.demo.models.Actor;
import org.efrei.demo.repository.ActorRepository;
import org.efrei.demo.dto.CreateMovie;
import org.efrei.demo.models.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ActorService {

    private final ActorRepository repository;
    private final MovieService movieService;

    @Autowired
    public ActorService(ActorRepository repository, MovieService movieService) {
        this.repository = repository;
        this.movieService = movieService;
    }

    public List<Actor> findAll(){
        // SELECT * from actor
        System.out.println(repository.findAll());
        return repository.findAll();
    }

    public void create(CreateActor createActor) {
        Actor actor = new Actor();
        Movie movie = movieService.findById(createActor.getMovieId());
        actor.setFirstname(createActor.getFirstName());
        actor.setName(createActor.getName());
        actor.setMovie(movie);
        repository.save(actor);
    }

    public Actor findById(String id) {
        // SELECT * FROM actor WERE id = :id
        return repository.findById(id).orElse(null);
    }

    public void deleteById(String id) {
        repository.deleteById(id);
    }

    public void update(String id, Actor actor) {

        //UPDATE actor SET (firstname, name) VALUES (:firstname, :name) where id = :id;
        Actor updatedActor = findById(id);
        updatedActor.setName(actor.getName());
        updatedActor.setFirstname(actor.getFirstname());
        repository.save(updatedActor);
    }



}