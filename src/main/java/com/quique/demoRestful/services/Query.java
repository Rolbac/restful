package com.quique.demoRestful.services;

import org.springframework.http.ResponseEntity;
//CQRS(Command-Query Responsibility Segregation)query=retrieve data
public interface Query <I,O>{
    ResponseEntity<O> execute(I input);
}
