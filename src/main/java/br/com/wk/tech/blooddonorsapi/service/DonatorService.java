package br.com.wk.tech.blooddonorsapi.service;

import br.com.wk.tech.blooddonorsapi.dto.DonatorDTO;

import java.util.List;

public interface DonatorService {

    DonatorDTO saveBloodDonator(DonatorDTO donatorDTO);

    DonatorDTO updateBloodDonator(DonatorDTO donatorDTO);

    List<DonatorDTO> getAllDonors();

    Long deleteDonator(Long donatorId);

    DonatorDTO getDonatorById(Long donatorId);

}
