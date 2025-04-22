package com.quique.demoRestful.repositories;

import com.quique.demoRestful.entities.Region;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegionRepo extends JpaRepository<Region, Integer> {
}
