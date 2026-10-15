package com.gaofeiyang.wms.service.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Goods {
    @JsonProperty("GoodsId")
    private String id;
    @JsonProperty("Name")
    private String name;
    @JsonProperty("Quantity")
    private int quantity;
}
