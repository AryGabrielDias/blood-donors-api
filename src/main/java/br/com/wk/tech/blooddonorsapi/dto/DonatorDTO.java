package br.com.wk.tech.blooddonorsapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DonatorDTO {
    private Long id;
    private String name;
    private String cpf;
    private String rg;
    private String birthDate;
    private String gender;
    private String mothersName;
    private String fathersName;
    private String email;
    private String zipCode;
    private String address;
    private Integer addressNumber;
    private String neighborhood;
    private String city;
    private String state;
    private String phoneNumber;
    private String celPhoneNumber;
    private Double height;
    private Integer weight;
    private String bloodType;
}
