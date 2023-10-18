package br.com.wk.tech.blooddonorsapi.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "tipo_sanguineo",
        uniqueConstraints = {@UniqueConstraint(columnNames = "id")})
@Data
public class BloodType {

    @Id
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "tipo", nullable = false)
    private String type;

    @OneToOne(mappedBy = "bloodType",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private Donator donors;
}
