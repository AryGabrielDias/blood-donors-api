package br.com.wk.tech.blooddonorsapi.service.impl;

import br.com.wk.tech.blooddonorsapi.dto.*;
import br.com.wk.tech.blooddonorsapi.model.BloodType;
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

        var donorAgeGroupBmiDTOList = new ArrayList<DonorAgeGroupBmiDTO>();

        var donorsList = donatorRepository.findAll();

        for (Donator donator : donorsList) {

            var donatorAge = LocalDateTime.now().getYear() - donator.getBirthDate().getYear();

            var dto = this.getAgeGroupAndBMI(donatorAge, donator.getWeight(), donator.getHeight());

            donorAgeGroupBmiDTOList.add(dto);
        }

        return this.getContextAverage(donorAgeGroupBmiDTOList);
    }

    @Override
    public List<GenderObesePercentageDTO> getGenderObesePercentage() {

        var donorGenderBMIDTOList = new ArrayList<DonorGenderBMIDTO>();

        var donorsList = donatorRepository.findAll();

        for (Donator donator : donorsList) {
            var dto = this.getGenderAndBMI(
                    donator.getGender(), donator.getWeight(), donator.getHeight());

            donorGenderBMIDTOList.add(dto);
        }

        return this.getObesePercentage(donorGenderBMIDTOList);
    }

    @Override
    public List<BloodTypeAverageAgeDTO> getBloodTypeAverageAge() {

        var bloodTypeAverageAgeDTOList = new ArrayList<BloodTypeAverageAgeDTO>();

        var donorsList = donatorRepository.findAll();

        var bloodTypeList = bloodTypeRepository.findAll();

        for (BloodType bloodType : bloodTypeList) {

            var donorsNumber = donorsList.stream()
                    .filter(obj -> obj.getBloodType().getType().equals(bloodType.getType()))
                    .mapToLong(Donator::getId).sum();

            var ageSum = donorsList.stream()
                    .filter(obj -> obj.getBloodType().getType().equals(bloodType.getType()))
                    .mapToInt(obj -> LocalDateTime.now().getYear() - obj.getBirthDate().getYear()).sum();

            var dto = new BloodTypeAverageAgeDTO();
            dto.setBloodType(bloodType.getType());
            dto.setAverageAge((double) (ageSum / donorsNumber));

            bloodTypeAverageAgeDTOList.add(dto);
        }


        return bloodTypeAverageAgeDTOList;
    }

    @Override
    public List<BloodTypePotentialDonorsDTO> getBloodTypePotentialDonors() {

        var bloodTypePotentialDonorsDTOList = new ArrayList<BloodTypePotentialDonorsDTO>();

        var donorsList = donatorRepository.findAll();

        var bloodTypeList = bloodTypeRepository.findAll();

        for (BloodType bloodType : bloodTypeList) {
            var dto = this.getPotentialDonorsForBloodType(donorsList, bloodType.getType());
            bloodTypePotentialDonorsDTOList.add(dto);
        }

        return bloodTypePotentialDonorsDTOList;
    }

    private List<GenderObesePercentageDTO> getObesePercentage(List<DonorGenderBMIDTO> list) {
        var genderObesePercentageDTOList = new ArrayList<GenderObesePercentageDTO>();

        var genderList = new ArrayList<String>();
        genderList.add("Masculino");
        genderList.add("Feminino");

        for (String gender : genderList) {
            var dto = new GenderObesePercentageDTO();

            var totalGenderDonors = list.stream()
                    .filter(obj -> obj.getGender().equals(gender))
                    .mapToInt(DonorGenderBMIDTO::getCount).sum();

            var obeseNumber = list.stream()
                    .filter(obj -> obj.getGender().equals(gender))
                    .filter(DonorGenderBMIDTO::isObese)
                    .mapToInt(DonorGenderBMIDTO::getCount).sum();

            var percentage = (obeseNumber * 100) / totalGenderDonors;

            dto.setGender(gender);
            dto.setObesePercentage(percentage + "%");

            genderObesePercentageDTOList.add(dto);

        }

        return genderObesePercentageDTOList;
    }

    private List<AgeGroupAverageBMIDTO> getContextAverage(List<DonorAgeGroupBmiDTO> list) {

        var ageGroupAverageBMIDTOList = new ArrayList<AgeGroupAverageBMIDTO>();

        var ageGroupList = new ArrayList<String>();
        ageGroupList.add("Faixa de 0 a 10");
        ageGroupList.add("Faixa de 11 a 20");
        ageGroupList.add("Faixa de 21 a 30");
        ageGroupList.add("Faixa de 31 a 40");
        ageGroupList.add("Faixa de 41 a 50");
        ageGroupList.add("Faixa de 51 a 60");
        ageGroupList.add("Faixa de 61 a 70");
        ageGroupList.add("Faixa de 71 a 80");
        ageGroupList.add("Faixa Acima de 80");

        for (String ageGroup : ageGroupList) {
            var dto = new AgeGroupAverageBMIDTO();

            var donorsNumber = list.stream().filter(
                    obj -> obj.getAgeGroup().equals(ageGroup))
                    .mapToInt(DonorAgeGroupBmiDTO::getCount).sum();

            var bmiSum = list.stream().filter(
                            obj -> obj.getAgeGroup().equals(ageGroup))
                    .mapToInt(DonorAgeGroupBmiDTO::getCount).sum();

            dto.setAgeGroup(ageGroup);
            dto.setAverageBMI((double) (bmiSum / donorsNumber));

            ageGroupAverageBMIDTOList.add(dto);
        }

        return ageGroupAverageBMIDTOList;
    }

    private DonorGenderBMIDTO getGenderAndBMI(String gender, Integer weight, Double height) {
        var dto = new DonorGenderBMIDTO();
        dto.setGender(gender);
        dto.setBMI(this.calculateBMI(weight, height));
        dto.setObese(this.calculateBMI(weight, height) > 30);
        dto.setCount(1);
        return dto;
    }

    private DonorAgeGroupBmiDTO getAgeGroupAndBMI(Integer donatorAge, Integer weight, Double height) {
        var dto = new DonorAgeGroupBmiDTO();

        if (donatorAge >= 0 && donatorAge < 11) {
            dto.setAgeGroup("Faixa de 0 a 10");
            dto.setAge(donatorAge);
            dto.setBMI(this.calculateBMI(weight, height));
            dto.setCount(1);
        }
        else if (donatorAge >= 11 && donatorAge < 21) {
            dto.setAgeGroup("Faixa de 11 a 20");
            dto.setAge(donatorAge);
            dto.setBMI(this.calculateBMI(weight, height));
            dto.setCount(1);
        }
        else if (donatorAge >= 21 && donatorAge < 31) {
            dto.setAgeGroup("Faixa de 21 a 30");
            dto.setAge(donatorAge);
            dto.setBMI(this.calculateBMI(weight, height));
            dto.setCount(1);
        }
        else if (donatorAge >= 31 && donatorAge < 41) {
            dto.setAgeGroup("Faixa de 31 a 40");
            dto.setAge(donatorAge);
            dto.setBMI(this.calculateBMI(weight, height));
            dto.setCount(1);
        }
        else if (donatorAge >= 41 && donatorAge < 51) {
            dto.setAgeGroup("Faixa de 41 a 50");
            dto.setAge(donatorAge);
            dto.setBMI(this.calculateBMI(weight, height));
            dto.setCount(1);
        }
        else if (donatorAge >= 51 && donatorAge < 61) {
            dto.setAgeGroup("Faixa de 51 a 60");
            dto.setAge(donatorAge);
            dto.setBMI(this.calculateBMI(weight, height));
            dto.setCount(1);
        }
        else if (donatorAge >= 61 && donatorAge < 71) {
            dto.setAgeGroup("Faixa de 61 a 70");
            dto.setAge(donatorAge);
            dto.setBMI(this.calculateBMI(weight, height));
            dto.setCount(1);
        }
        else if (donatorAge >= 71 && donatorAge < 81) {
            dto.setAgeGroup("Faixa de 71 a 80");
            dto.setAge(donatorAge);
            dto.setBMI(this.calculateBMI(weight, height));
            dto.setCount(1);
        }
        else {
            dto.setAgeGroup("Faixa Acima de 80");
            dto.setAge(donatorAge);
            dto.setBMI(this.calculateBMI(weight, height));
            dto.setCount(1);
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

    private BloodTypePotentialDonorsDTO getPotentialDonorsForBloodType(List<Donator> donorsList, String bloodType) {

        var dto = new BloodTypePotentialDonorsDTO();

        switch (bloodType) {
            case "A+" -> {

                var potentialDonorsNumber = donorsList.stream()
                        .filter(obj -> obj.getWeight() > 50)
                        .filter(obj -> (LocalDateTime.now().getYear() - obj.getBirthDate().getYear()) > 15 &&
                                (LocalDateTime.now().getYear() - obj.getBirthDate().getYear()) < 70)
                        .filter(obj -> obj.getBloodType().getType().equals("A+") || obj.getBloodType().getType().equals("A-") ||
                                obj.getBloodType().getType().equals("O+") || obj.getBloodType().getType().equals("O-"))
                        .mapToLong(Donator::getId)
                        .sum();

                dto.setBloodType(bloodType);
                dto.setNumberOfPotentialDonors((double) potentialDonorsNumber);
            }
            case "A-" -> {

                var potentialDonorsNumber = donorsList.stream()
                        .filter(obj -> obj.getWeight() > 50)
                        .filter(obj -> (LocalDateTime.now().getYear() - obj.getBirthDate().getYear()) > 15 &&
                                (LocalDateTime.now().getYear() - obj.getBirthDate().getYear()) < 70)
                        .filter(obj -> obj.getBloodType().getType().equals("A-") ||
                                obj.getBloodType().getType().equals("O-"))
                        .mapToLong(Donator::getId)
                        .sum();

                dto.setBloodType(bloodType);
                dto.setNumberOfPotentialDonors((double) potentialDonorsNumber);
            }
            case "B+" -> {

                var potentialDonorsNumber = donorsList.stream()
                        .filter(obj -> obj.getWeight() > 50)
                        .filter(obj -> (LocalDateTime.now().getYear() - obj.getBirthDate().getYear()) > 15 &&
                                (LocalDateTime.now().getYear() - obj.getBirthDate().getYear()) < 70)
                        .filter(obj -> obj.getBloodType().getType().equals("B+") || obj.getBloodType().getType().equals("B-") ||
                                obj.getBloodType().getType().equals("O+") || obj.getBloodType().getType().equals("O-"))
                        .mapToLong(Donator::getId)
                        .sum();

                dto.setBloodType(bloodType);
                dto.setNumberOfPotentialDonors((double) potentialDonorsNumber);
            }
            case "B-" -> {

                var potentialDonorsNumber = donorsList.stream()
                        .filter(obj -> obj.getWeight() > 50)
                        .filter(obj -> (LocalDateTime.now().getYear() - obj.getBirthDate().getYear()) > 15 &&
                                (LocalDateTime.now().getYear() - obj.getBirthDate().getYear()) < 70)
                        .filter(obj -> obj.getBloodType().getType().equals("B-") ||
                                obj.getBloodType().getType().equals("O-"))
                        .mapToLong(Donator::getId)
                        .sum();

                dto.setBloodType(bloodType);
                dto.setNumberOfPotentialDonors((double) potentialDonorsNumber);
            }
            case "AB+" -> {

                var potentialDonorsNumber = donorsList.stream()
                        .filter(obj -> obj.getWeight() > 50)
                        .filter(obj -> (LocalDateTime.now().getYear() - obj.getBirthDate().getYear()) > 15 &&
                                (LocalDateTime.now().getYear() - obj.getBirthDate().getYear()) < 70)
                        .filter(obj -> obj.getBloodType().getType().equals("A+") || obj.getBloodType().getType().equals("B+") ||
                                obj.getBloodType().getType().equals("O+") || obj.getBloodType().getType().equals("AB+") ||
                                obj.getBloodType().getType().equals("A-") || obj.getBloodType().getType().equals("B-") ||
                                obj.getBloodType().getType().equals("O-") || obj.getBloodType().getType().equals("AB-"))
                        .mapToLong(Donator::getId)
                        .sum();

                dto.setBloodType(bloodType);
                dto.setNumberOfPotentialDonors((double) potentialDonorsNumber);
            }
            case "AB-" -> {

                var potentialDonorsNumber = donorsList.stream()
                        .filter(obj -> obj.getWeight() > 50)
                        .filter(obj -> (LocalDateTime.now().getYear() - obj.getBirthDate().getYear()) > 15 &&
                                (LocalDateTime.now().getYear() - obj.getBirthDate().getYear()) < 70)
                        .filter(obj -> obj.getBloodType().getType().equals("A-") || obj.getBloodType().getType().equals("B-") ||
                                obj.getBloodType().getType().equals("O-") || obj.getBloodType().getType().equals("AB-"))
                        .mapToLong(Donator::getId)
                        .sum();

                dto.setBloodType(bloodType);
                dto.setNumberOfPotentialDonors((double) potentialDonorsNumber);
            }
            case "O+" -> {

                var potentialDonorsNumber = donorsList.stream()
                        .filter(obj -> obj.getWeight() > 50)
                        .filter(obj -> (LocalDateTime.now().getYear() - obj.getBirthDate().getYear()) > 15 &&
                                (LocalDateTime.now().getYear() - obj.getBirthDate().getYear()) < 70)
                        .filter(obj -> obj.getBloodType().getType().equals("O+") ||
                                obj.getBloodType().getType().equals("O-"))
                        .mapToLong(Donator::getId)
                        .sum();

                dto.setBloodType(bloodType);
                dto.setNumberOfPotentialDonors((double) potentialDonorsNumber);
            }
            default -> {

                var potentialDonorsNumber = donorsList.stream()
                        .filter(obj -> obj.getWeight() > 50)
                        .filter(obj -> (LocalDateTime.now().getYear() - obj.getBirthDate().getYear()) > 15 &&
                                (LocalDateTime.now().getYear() - obj.getBirthDate().getYear()) < 70)
                        .filter(obj -> obj.getBloodType().getType().equals("O-"))
                        .mapToLong(Donator::getId)
                        .sum();

                dto.setBloodType(bloodType);
                dto.setNumberOfPotentialDonors((double) potentialDonorsNumber);

            }
        }

        return dto;
    }
}
