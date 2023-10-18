package br.com.wk.tech.blooddonorsapi.service;

import br.com.wk.tech.blooddonorsapi.dto.BloodTypeDTO;

import java.util.List;

public interface BloodTypeService {

    List<BloodTypeDTO> getAllBloodTypes();
}
