package com.quique.demoRestful.data;

import com.quique.demoRestful.entities.Region;

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
}
