package br.com.wk.tech.blooddonorsapi.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AgeGroupAverageBMIDTO {
    private String ageGroup;
    private Double averageBMI;
}
