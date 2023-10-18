package br.com.wk.tech.blooddonorsapi.service.impl;

import br.com.wk.tech.blooddonorsapi.dto.DonatorDTO;
import br.com.wk.tech.blooddonorsapi.model.Donator;
import br.com.wk.tech.blooddonorsapi.repository.DonatorRepository;
import br.com.wk.tech.blooddonorsapi.service.DonatorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class DonatorServiceImpl implements DonatorService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DonatorServiceImpl.class);

    private final DonatorRepository donorsRepository;

    @Autowired
    public DonatorServiceImpl(DonatorRepository donorsRepository) {
        this.donorsRepository = donorsRepository;
    }

    @Override
    @Transactional
    public DonatorDTO saveBloodDonator(DonatorDTO donatorDTO) {
        return null;
    }

    @Override
    @Transactional
    public DonatorDTO updateBloodDonator(DonatorDTO donatorDTO) {
        return null;
    }

    @Override
    public List<DonatorDTO> getAllDonors() {

        var donatorDtoList = new ArrayList<DonatorDTO>();

        try {
            var donatorList = donorsRepository.findAll();

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
            donorsRepository.deleteById(donatorId);
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
                    .entityToDto(donorsRepository.findByDonatorId(donatorId));

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
        return donator;
    }

    private String convertLocalDateTimeToString(LocalDateTime dateTime) {
        var dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return dateTime.format(dateTimeFormatter);
    }
}
