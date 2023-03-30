package com.example.demo;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.InputStream;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;

import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.springframework.stereotype.Service;

import org.springframework.stereotype.Service;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public void uploadFile(String filePath) throws IOException, OWLOntologyCreationException {
        try (InputStream inputStream = new FileInputStream(filePath)) {
            OWLOntology ontology = OWLManager.createOWLOntologyManager().loadOntologyFromOntologyDocument(inputStream);

            // iterate through individuals in the ontology and save them to the repository
            OWLDataFactory factory = OWLManager.getOWLDataFactory();
            for (OWLNamedIndividual individual : ontology.getIndividualsInSignature()) {
                String name = individual.getIRI().getShortForm();
                personRepository.save(new Person(name));
            }
        } catch (OWLOntologyCreationException e) {
            e.printStackTrace();
            throw e;
        }
    }
}


