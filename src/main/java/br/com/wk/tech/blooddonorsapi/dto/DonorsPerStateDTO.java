package br.com.wk.tech.blooddonorsapi.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DonorsPerStateDTO {
    private String state;
    private Integer numberOfDonors;
}
