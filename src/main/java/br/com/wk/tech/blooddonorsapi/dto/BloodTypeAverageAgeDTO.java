package br.com.wk.tech.blooddonorsapi.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BloodTypeAverageAgeDTO {
    private String bloodType;
    private Double averageAge;
}
