package com.denys.hibernateexample.controller;


import com.denys.hibernateexample.db.service.PersonService;
import com.denys.hibernateexample.db.service.ProfessorService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HomeController {
    @NonNull
    private PersonService personService;
    @NonNull
    private ProfessorService professorService;

    @GetMapping("/test")
    String test() {
        return professorService.test().toString();
    }
}
