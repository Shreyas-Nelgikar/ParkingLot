package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class User extends BaseModel {

    private String userName;
    private Long phoneNumber;
    private String address;

}
