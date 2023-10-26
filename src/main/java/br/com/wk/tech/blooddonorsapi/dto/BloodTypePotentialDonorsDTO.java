package br.com.wk.tech.blooddonorsapi.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BloodTypePotentialDonorsDTO {
    private String bloodType;
    private Integer numberOfPotentialDonors;
}
