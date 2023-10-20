package br.com.wk.tech.blooddonorsapi.controller;

import br.com.wk.tech.blooddonorsapi.dto.*;
import br.com.wk.tech.blooddonorsapi.service.BloodBankStatisticsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v0/api/bank-statistics")
public class BloodBankStatisticsController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BloodBankStatisticsController.class);

    private final BloodBankStatisticsService bloodBankStatisticsService;

    @Autowired
    public BloodBankStatisticsController(BloodBankStatisticsService bloodBankStatisticsService) {
        this.bloodBankStatisticsService = bloodBankStatisticsService;
    }

    @GetMapping("/donors-per-state")
    public ResponseEntity<List<DonorsPerStateDTO>> getNumberOfDonorsPerState() {
        return new ResponseEntity<>(
                bloodBankStatisticsService.getNumberOfDonorsPerState(), HttpStatus.OK);
    }

    @GetMapping("/age-group-average-bmi")
    public ResponseEntity<List<AgeGroupAverageBMIDTO>> getAgeGroupAverageBMI() {
        return new ResponseEntity<>(
                bloodBankStatisticsService.getAgeGroupAverageBMI(), HttpStatus.OK);
    }

    @GetMapping("/gender-obese-percentage")
    public ResponseEntity<List<GenderObesePercentageDTO>> getGenderObesePercentage() {
        return new ResponseEntity<>(
                bloodBankStatisticsService.getGenderObesePercentage(), HttpStatus.OK);
    }

    @GetMapping("/blood-type-average-age")
    public ResponseEntity<List<BloodTypeAverageAgeDTO>> getBloodTypeAverageAge() {
        return new ResponseEntity<>(
                bloodBankStatisticsService.getBloodTypeAverageAge(), HttpStatus.OK);
    }

    @GetMapping("/blood-type-potential-donors")
    public ResponseEntity<List<BloodTypePotentialDonorsDTO>> getBloodTypePotentialDonors() {
        return new ResponseEntity<>(
                bloodBankStatisticsService.getBloodTypePotentialDonors(), HttpStatus.OK);
    }

}
