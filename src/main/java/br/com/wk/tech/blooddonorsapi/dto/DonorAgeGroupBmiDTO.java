package br.com.wk.tech.blooddonorsapi.dto;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DonorAgeGroupBmiDTO {
    private String ageGroup;
    private Integer age;
    private Double BMI;
    private Integer count;
}
