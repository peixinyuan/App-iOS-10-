package com.gaofeiyang.wms.service.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Floor {
    @JsonProperty("FloorId")
    private String id;

    @JsonProperty("Name")
    private String name;

    @JsonProperty("ShelvesId")
    private String shelvesId;
}
