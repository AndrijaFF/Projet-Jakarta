package org.efrei.demo.controllers;

import org.efrei.demo.models.Actor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class DefaultController {

    @GetMapping("hello-world")
    public Actor cheers(){
        Actor actor = new Actor("name", "firstname");
        return actor;
    }
}
