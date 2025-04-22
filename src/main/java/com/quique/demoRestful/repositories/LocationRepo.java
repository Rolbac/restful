package com.quique.demoRestful.repositories;

import com.quique.demoRestful.entities.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepo extends JpaRepository<Location, Integer> {
}
