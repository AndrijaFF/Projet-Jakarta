package org.efrei.demo.controllers;

import org.efrei.demo.dto.CreateSalle;
import org.efrei.demo.models.Salle;
import org.efrei.demo.services.SalleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/salles")
public class SalleController {

    private final SalleService service;

    @Autowired
    public SalleController(SalleService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Salle>> findAll() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Salle> findById(@PathVariable String id) {
        Salle salle = service.findById(id);
        if (salle == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(salle, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody CreateSalle createSalle) {
        try {
            Salle salle = service.create(createSalle);
            return new ResponseEntity<>(salle, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable String id, @RequestBody Salle salle) {
        service.update(id, salle);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable String id) {
        service.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
