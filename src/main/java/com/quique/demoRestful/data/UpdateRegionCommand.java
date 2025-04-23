package com.quique.demoRestful.data;

import com.quique.demoRestful.entities.Region;

public class UpdateRegionCommand {
    private Integer id;
    private Region region;

    public UpdateRegionCommand(Integer id, Region region) {
        this.id = id;
        this.region = region;
    }

    public Integer getId() {
        return id;
    }

    public Region getRegion() {
        return region;
    }
}
