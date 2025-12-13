package org.example.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class BaseModel {

    private Long id;
    private Date createdAt;
    private Date modifiedAt;

}
