package br.com.wk.tech.blooddonorsapi.service.impl;

import br.com.wk.tech.blooddonorsapi.dto.DonatorDTO;
import br.com.wk.tech.blooddonorsapi.model.Donator;
import br.com.wk.tech.blooddonorsapi.repository.BloodTypeRepository;
import br.com.wk.tech.blooddonorsapi.repository.DonatorRepository;
import br.com.wk.tech.blooddonorsapi.service.DonatorService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class DonatorServiceImpl implements DonatorService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DonatorServiceImpl.class);

    private final DonatorRepository donatorRepository;

    private final BloodTypeRepository bloodTypeRepository;

    @Autowired
    public DonatorServiceImpl(DonatorRepository donatorRepository, BloodTypeRepository bloodTypeRepository) {
        this.bloodTypeRepository = bloodTypeRepository;
        this.donatorRepository = donatorRepository;
    }

    @Override
    @Transactional
    public DonatorDTO saveBloodDonator(DonatorDTO donatorDTO) {

        var dto = new DonatorDTO();

        try {
            var donator = this.dtoToEntity(donatorDTO);

            dto = this.entityToDto(donatorRepository.save(donator));
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
            var donator = this.dtoToEntity(donatorDTO);

            dto = this.entityToDto(donatorRepository.saveAndFlush(donator));
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
                donatorDtoList.add(this.entityToDto(donator));
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
            donatorDto = this
                    .entityToDto(donatorRepository.findByDonatorId(donatorId));

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }

        return donatorDto;
    }

    private DonatorDTO entityToDto(Donator donator) {
        var dto = new DonatorDTO();
        dto.setId(donator.getId());
        dto.setName(donator.getName());
        dto.setCpf(dto.getCpf());
        dto.setRg(dto.getRg());
        dto.setBirthDate(this
                .convertLocalDateTimeToString(donator.getBirthDate()));
        dto.setGender(donator.getGender());
        dto.setMothersName(donator.getMothersName());
        dto.setFathersName(donator.getFathersName());
        dto.setEmail(donator.getEmail());
        dto.setZipCode(donator.getZipCode());
        dto.setAddress(donator.getAddress());
        dto.setAddressNumber(donator.getAddressNumber());
        dto.setNeighborhood(donator.getNeighborhood());
        dto.setCity(donator.getCity());
        dto.setState(donator.getState());
        dto.setPhoneNumber(donator.getPhoneNumber());
        dto.setCelPhoneNumber(donator.getCelPhoneNumber());
        dto.setHeight(donator.getHeight());
        dto.setWeight(donator.getWeight());
        dto.setBloodType(donator.getBloodType().getType());
        return dto;
    }

    private Donator dtoToEntity(DonatorDTO donatorDTO) {
        var donator = new Donator();
        donator.setId(donatorDTO.getId());
        donator.setName(donatorDTO.getName());
        donator.setCpf(donatorDTO.getCpf());
        donator.setRg(donatorDTO.getRg());
        donator.setBirthDate(this
                .convertStringDateToLocalDateTime(donatorDTO.getBirthDate()));
        donator.setGender(donatorDTO.getGender());
        donator.setMothersName(donatorDTO.getMothersName());
        donator.setFathersName(donatorDTO.getFathersName());
        donator.setEmail(donatorDTO.getEmail());
        donator.setZipCode(donatorDTO.getZipCode());
        donator.setAddress(donatorDTO.getAddress());
        donator.setAddressNumber(donatorDTO.getAddressNumber());
        donator.setNeighborhood(donatorDTO.getNeighborhood());
        donator.setCity(donatorDTO.getCity());
        donator.setState(donator.getState());
        donator.setPhoneNumber(donatorDTO.getPhoneNumber());
        donator.setCelPhoneNumber(donatorDTO.getCelPhoneNumber());
        donator.setHeight(donatorDTO.getHeight());
        donator.setWeight(donatorDTO.getWeight());
        donator.setBloodType(bloodTypeRepository
                .findByBloodType(donatorDTO.getBloodType()));
        return donator;
    }

    private String convertLocalDateTimeToString(LocalDateTime dateTime) {
        var dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return dateTime.format(dateTimeFormatter);
    }

    public LocalDateTime convertStringDateToLocalDateTime(String date) {
        var parser = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(date, parser).atStartOfDay();
    }
}
