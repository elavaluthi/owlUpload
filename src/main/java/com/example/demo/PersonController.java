package com.example.demo;

import java.io.IOException;

import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) throws IOException, OWLOntologyCreationException {
        personService.uploadFile(file.getOriginalFilename());
        return ResponseEntity.ok("File uploaded successfully.");
    }
}

