package com.gaofeiyang.wms.service.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class User {
    @JsonProperty("id")
    private String id;

    @JsonProperty("Name")
    private String name;

    @JsonProperty("Password")
    private String password;

    @JsonProperty("Type")
    private int type;

    @JsonProperty("Sex")
    private int sex;

    @JsonProperty("Age")
    private int age;

    @JsonProperty("Email")
    private String email;
}
