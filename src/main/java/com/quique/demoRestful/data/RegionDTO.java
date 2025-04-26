package com.quique.demoRestful.data;

import com.quique.demoRestful.entities.Region;

import java.util.Objects;

public class RegionDTO {
    private String name;

    public RegionDTO(Region region){
        this.name = region.getName();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        RegionDTO regionDTO = (RegionDTO) o;
        return Objects.equals(name, regionDTO.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }
}
