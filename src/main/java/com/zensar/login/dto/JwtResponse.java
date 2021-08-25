package com.zensar.login.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtResponse {

    private String token;
    private String type = "Bearer";
    private Integer id;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;

    public JwtResponse(String accessToken,
                       Integer id,
                       String firstName,
                       String lastName,
                       String phone,
                       String email) {
        this.token = accessToken;
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
       
    }

    public JwtResponse(Integer id,
                       String firstName,
                       String lastName,
                       String phone,
                       String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;

    }


}
