package br.com.wk.tech.blooddonorsapi.service.impl;

import br.com.wk.tech.blooddonorsapi.dto.DonatorDTO;
import br.com.wk.tech.blooddonorsapi.model.Donator;
import br.com.wk.tech.blooddonorsapi.repository.DonatorRepository;
import br.com.wk.tech.blooddonorsapi.service.DonatorService;
import br.com.wk.tech.blooddonorsapi.utils.Utils;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DonatorServiceImpl implements DonatorService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DonatorServiceImpl.class);

    private final DonatorRepository donatorRepository;

    private final Utils utils = new Utils();

    @Autowired
    public DonatorServiceImpl(DonatorRepository donatorRepository) {
        this.donatorRepository = donatorRepository;
    }

    @Override
    @Transactional
    public DonatorDTO saveBloodDonator(DonatorDTO donatorDTO) {

        var dto = new DonatorDTO();

        try {
            var donator = utils.dtoToEntity(donatorDTO);

            dto = utils.entityToDto(donatorRepository.save(donator));
        }
        catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return dto;
    }

    @Override
    @Transactional
    public DonatorDTO updateBloodDonator(DonatorDTO donatorDTO) {

        var dto = new DonatorDTO();

        try {
            var donator = utils.dtoToEntity(donatorDTO);

            dto = utils.entityToDto(donatorRepository.saveAndFlush(donator));
        }
        catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return dto;

    }

    @Override
    public List<DonatorDTO> getAllDonors() {

        var donatorDtoList = new ArrayList<DonatorDTO>();

        try {
            var donatorList = donatorRepository.findAll();

            for (Donator donator : donatorList) {
                donatorDtoList.add(utils.entityToDto(donator));
            }
        }
        catch (Exception e) {
            LOGGER.error(e.getMessage());
        }

        return donatorDtoList;
    }

    @Override
    @Transactional
    public Long deleteDonator(Long donatorId) {

        try {
            donatorRepository.deleteById(donatorId);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return donatorId;
    }

    @Override
    public DonatorDTO getDonatorById(Long donatorId) {

        var donatorDto = new DonatorDTO();

        try {
            donatorDto = utils.entityToDto(donatorRepository.findByDonatorId(donatorId));

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }

        return donatorDto;
    }

}
