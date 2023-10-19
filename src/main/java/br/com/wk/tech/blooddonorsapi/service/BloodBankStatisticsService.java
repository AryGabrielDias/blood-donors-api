package br.com.wk.tech.blooddonorsapi.service;

import br.com.wk.tech.blooddonorsapi.dto.*;

import java.util.List;

public interface BloodBankStatisticsService {

    List<DonorsPerStateDTO> getNumberOfDonorsPerState();

    List<AgeGroupAverageBMIDTO> getAgeGroupAverageBMI();

    List<GenderObesePercentageDTO> getGenderObesePercentage();

    List<BloodTypeAverageAgeDTO> getBloodTypeAverageAge();

    List<BloodTypePotentialDonorsDTO> getBloodTypePotentialDonors();

}
