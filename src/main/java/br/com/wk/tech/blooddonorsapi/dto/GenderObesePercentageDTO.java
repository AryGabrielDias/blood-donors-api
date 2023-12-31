package br.com.wk.tech.blooddonorsapi.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GenderObesePercentageDTO {
    private String gender;
    private String obesePercentage;
}
