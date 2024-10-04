package org.efrei.demo.services;

import org.efrei.demo.models.Actor;
import org.efrei.demo.repository.ActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActorService {

    private final ActorRepository repository;

    @Autowired
    public ActorService(ActorRepository repository) {
        this.repository = repository;
    }

    public List<Actor> findAll(){
        // SELECT * from actor
        System.out.println(repository.findAll());
        return repository.findAll();
    }

    public void create(Actor actor) {
        // INSERT INTO actor VALUES(":firstname", ":values")
        repository.save(actor);
    }


    public Actor findById(String id) {
        return repository.findById(id).orElse(null);
    }

    public void deleteById(String id){
        repository.deleteById(id);
    }
}