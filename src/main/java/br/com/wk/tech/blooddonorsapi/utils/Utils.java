package br.com.wk.tech.blooddonorsapi.utils;

import br.com.wk.tech.blooddonorsapi.dto.DonatorDTO;
import br.com.wk.tech.blooddonorsapi.model.Donator;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@NoArgsConstructor
public class Utils {

    public Donator dtoToEntity(DonatorDTO donatorDTO) {
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
        donator.setHeight(String.valueOf(donatorDTO.getHeight()));
        donator.setWeight(donatorDTO.getWeight());
        donator.setBloodType(donatorDTO.getBloodType());
        return donator;
    }

    public DonatorDTO entityToDto(Donator donator) {
        var dto = new DonatorDTO();
        dto.setId(donator.getId());
        dto.setName(donator.getName());
        dto.setCpf(donator.getCpf());
        dto.setRg(donator.getRg());
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
        dto.setHeight(Double.valueOf(donator.getHeight()));
        dto.setWeight(donator.getWeight());
        dto.setBloodType(donator.getBloodType());
        return dto;
    }

    public LocalDateTime convertStringDateToLocalDateTime(String date) {
        var parser = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(date, parser).atStartOfDay();
    }

    public String convertLocalDateTimeToString(LocalDateTime dateTime) {
        var dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return dateTime.format(dateTimeFormatter);
    }
}
