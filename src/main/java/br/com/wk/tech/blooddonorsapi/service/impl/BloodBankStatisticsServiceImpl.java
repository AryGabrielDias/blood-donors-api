package br.com.wk.tech.blooddonorsapi.service.impl;

import br.com.wk.tech.blooddonorsapi.dto.*;
import br.com.wk.tech.blooddonorsapi.model.Donator;
import br.com.wk.tech.blooddonorsapi.repository.BloodTypeRepository;
import br.com.wk.tech.blooddonorsapi.repository.DonatorRepository;
import br.com.wk.tech.blooddonorsapi.service.BloodBankStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class BloodBankStatisticsServiceImpl implements BloodBankStatisticsService {

    private final DonatorRepository donatorRepository;

    private final BloodTypeRepository bloodTypeRepository;

    @Autowired
    public BloodBankStatisticsServiceImpl(DonatorRepository donatorRepository, BloodTypeRepository bloodTypeRepository) {
        this.donatorRepository = donatorRepository;
        this.bloodTypeRepository = bloodTypeRepository;
    }

    @Override
    public List<DonorsPerStateDTO> getNumberOfDonorsPerState() {

        var donorsPerStateDtoList = new ArrayList<DonorsPerStateDTO>();

        var donorsList = donatorRepository.findAll();

        var stateArray = this.getStateArray();

        for (String state : stateArray) {

            var donorsNumber = 0;

            for (Donator donator : donorsList) {

                if (state.equals(donator.getState())) {
                    donorsNumber++;
                }
            }

            var dto = new DonorsPerStateDTO();
            dto.setState(state);
            dto.setNumberOfDonors(donorsNumber);

            donorsPerStateDtoList.add(dto);
        }

        return donorsPerStateDtoList;
    }

    @Override
    public List<AgeGroupAverageBMIDTO> getAgeGroupAverageBMI() {

        var ageGroupAverageBMIDTOList = new ArrayList<AgeGroupAverageBMIDTO>();

        var donorAgeGroupBmiDTOList = new ArrayList<DonorAgeGroupBmiDTO>();

        var donorsList = donatorRepository.findAll();

        for (Donator donator : donorsList) {

            var donatorAge = LocalDateTime.now().getYear() - donator.getBirthDate().getYear();

            var dto = this.getAgeGroupAndBMI(donatorAge, donator.getWeight(), donator.getHeight());

            donorAgeGroupBmiDTOList.add(dto);
        }

        /*
        donorAgeGroupBmiDTOList.stream().filter(
                obj -> obj.getAgeGroup().equals("Faixa de 0 a 10"))
                .mapToDouble(DonorAgeGroupBmiDTO::getBMI).sum();

         */




        return null;
    }

    @Override
    public List<GenderObesePercentageDTO> getGenderObesePercentage() {
        return null;
    }

    @Override
    public List<BloodTypeAverageAgeDTO> getBloodTypeAverageAge() {
        return null;
    }

    @Override
    public List<BloodTypePotentialDonorsDTO> getBloodTypePotentialDonors() {
        return null;
    }

    private DonorAgeGroupBmiDTO getAgeGroupAndBMI(Integer donatorAge, Integer weight, Double height) {
        var dto = new DonorAgeGroupBmiDTO();

        if (donatorAge >= 0 && donatorAge < 11) {
            dto.setAgeGroup("Faixa de 0 a 10");
            dto.setAge(donatorAge);
            dto.setBMI(this.calculateBMI(weight, height));
        }
        else if (donatorAge >= 11 && donatorAge < 21) {
            dto.setAgeGroup("Faixa de 11 a 20");
            dto.setAge(donatorAge);
            dto.setBMI(this.calculateBMI(weight, height));
        }
        else if (donatorAge >= 21 && donatorAge < 31) {
            dto.setAgeGroup("Faixa de 21 a 30");
            dto.setAge(donatorAge);
            dto.setBMI(this.calculateBMI(weight, height));
        }
        else if (donatorAge >= 31 && donatorAge < 41) {
            dto.setAgeGroup("Faixa de 31 a 40");
            dto.setAge(donatorAge);
            dto.setBMI(this.calculateBMI(weight, height));
        }
        else if (donatorAge >= 41 && donatorAge < 51) {
            dto.setAgeGroup("Faixa de 41 a 50");
            dto.setAge(donatorAge);
            dto.setBMI(this.calculateBMI(weight, height));
        }
        else if (donatorAge >= 51 && donatorAge < 61) {
            dto.setAgeGroup("Faixa de 51 a 60");
            dto.setAge(donatorAge);
            dto.setBMI(this.calculateBMI(weight, height));
        }
        else if (donatorAge >= 61 && donatorAge < 71) {
            dto.setAgeGroup("Faixa de 61 a 70");
            dto.setAge(donatorAge);
            dto.setBMI(this.calculateBMI(weight, height));
        }
        else if (donatorAge >= 71 && donatorAge < 81) {
            dto.setAgeGroup("Faixa de 71 a 80");
            dto.setAge(donatorAge);
            dto.setBMI(this.calculateBMI(weight, height));
        }
        else {
            dto.setAgeGroup("Faixa Acima de 80");
            dto.setAge(donatorAge);
            dto.setBMI(this.calculateBMI(weight, height));
        }

        return dto;
    }

    private Double calculateBMI(Integer weight, Double height) {
        return weight / (height * height);
    }

    private List<String> getStateArray() {
        var stateArray = new ArrayList<String>();
        stateArray.add("AC");
        stateArray.add("AL");
        stateArray.add("AP");
        stateArray.add("AM");
        stateArray.add("BA");
        stateArray.add("CE");
        stateArray.add("DF");
        stateArray.add("ES");
        stateArray.add("GO");
        stateArray.add("MA");
        stateArray.add("MT");
        stateArray.add("MS");
        stateArray.add("MG");
        stateArray.add("PA");
        stateArray.add("PB");
        stateArray.add("PR");
        stateArray.add("PE");
        stateArray.add("PI");
        stateArray.add("RJ");
        stateArray.add("RN");
        stateArray.add("RS");
        stateArray.add("RO");
        stateArray.add("RR");
        stateArray.add("SC");
        stateArray.add("SE");
        stateArray.add("SP");
        stateArray.add("TO");
        return stateArray;
    }
}
