package br.com.wk.tech.blooddonorsapi.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DonorGenderBMIDTO {
    private String gender;
    private Double BMI;
    private boolean isObese;
    private Integer count;
}
