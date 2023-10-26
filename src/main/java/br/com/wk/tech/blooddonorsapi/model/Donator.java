package br.com.wk.tech.blooddonorsapi.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "doador",
        schema = "banco")
@Data
public class Donator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "nome", nullable = false)
    private String name;

    @Column(name = "cpf", nullable = false)
    private String cpf;

    @Column(name = "rg", nullable = false)
    private String rg;

    @Column(name = "data_nascimento", nullable = false)
    private LocalDateTime birthDate;

    @Column(name = "sexo", nullable = false)
    private String gender;

    @Column(name = "mae", nullable = false)
    private String mothersName;

    @Column(name = "pai", nullable = false)
    private String fathersName;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "cep", nullable = false)
    private String zipCode;

    @Column(name = "endereco", nullable = false)
    private String address;

    @Column(name = "numero", nullable = false)
    private Integer addressNumber;

    @Column(name = "bairro", nullable = false)
    private String neighborhood;

    @Column(name = "cidade", nullable = false)
    private String city;

    @Column(name = "estado", nullable = false)
    private String state;

    @Column(name = "telefone_fixo", nullable = false)
    private String phoneNumber;

    @Column(name = "celular", nullable = false)
    private String celPhoneNumber;

    @Column(name = "altura", nullable = false)
    private String height;

    @Column(name = "peso", nullable = false)
    private Integer weight;

    @Column(name = "tipo_sanguineo", nullable = false)
    private String bloodType;

}
