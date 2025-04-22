package com.quique.demoRestful.repositories;

import com.quique.demoRestful.entities.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepo extends JpaRepository<Country, String> {
}
