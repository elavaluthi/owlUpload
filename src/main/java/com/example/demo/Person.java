package com.example.demo;

import org.neo4j.ogm.annotation.NodeEntity;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;

import lombok.Data;
@Data
@NodeEntity
public class Person {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    public Person(String name) {
        this.name = name;
    }

    // getters and setters
    
}
