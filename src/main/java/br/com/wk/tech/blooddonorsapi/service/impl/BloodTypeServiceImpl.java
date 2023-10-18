package br.com.wk.tech.blooddonorsapi.service.impl;

import br.com.wk.tech.blooddonorsapi.dto.BloodTypeDTO;
import br.com.wk.tech.blooddonorsapi.model.BloodType;
import br.com.wk.tech.blooddonorsapi.repository.BloodTypeRepository;
import br.com.wk.tech.blooddonorsapi.service.BloodTypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BloodTypeServiceImpl implements BloodTypeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BloodTypeServiceImpl.class);

    private final BloodTypeRepository bloodTypeRepository;

    @Autowired
    public BloodTypeServiceImpl(BloodTypeRepository bloodTypeRepository) {
        this.bloodTypeRepository = bloodTypeRepository;
    }

    @Override
    public List<BloodTypeDTO> getAllBloodTypes() {

        var dtoList = new ArrayList<BloodTypeDTO>();

        try {
            var bloodTypeList = bloodTypeRepository.findAll();

            for (BloodType bloodType : bloodTypeList) {
                dtoList.add(this.entityToDto(bloodType));
            }
        }
        catch (Exception e) {
            LOGGER.error(e.getMessage());
        }

        return dtoList;
    }

    private BloodTypeDTO entityToDto(BloodType bloodType) {
        return new BloodTypeDTO(bloodType.getId(), bloodType.getType());
    }
}
