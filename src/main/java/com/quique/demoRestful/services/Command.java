package com.quique.demoRestful.services;

import org.springframework.http.ResponseEntity;
//CQRS(Command-Query Responsibility Segregation)command=edit data
public interface Command <I, O>{
    ResponseEntity<O> execute(I input);
}
