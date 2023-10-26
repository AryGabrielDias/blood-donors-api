package br.com.wk.tech.blooddonorsapi.controller;

import br.com.wk.tech.blooddonorsapi.dto.*;
import br.com.wk.tech.blooddonorsapi.service.BloodBankStatisticsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
    @Operation(summary = "Donors Per State", description = "Donors Per State")
    @ApiResponse(responseCode = "200", description = "OK",
            content = @Content(schema = @Schema(implementation = DonorsPerStateDTO.class)))
    public ResponseEntity<List<DonorsPerStateDTO>> getNumberOfDonorsPerState() {
        return new ResponseEntity<>(
                bloodBankStatisticsService.getNumberOfDonorsPerState(), HttpStatus.OK);
    }

    @GetMapping("/age-group-average-bmi")
    @Operation(summary = "Age Group Average BMI", description = "Age Group Average BMI")
    @ApiResponse(responseCode = "200", description = "OK",
            content = @Content(schema = @Schema(implementation = AgeGroupAverageBMIDTO.class)))
    public ResponseEntity<List<AgeGroupAverageBMIDTO>> getAgeGroupAverageBMI() {
        return new ResponseEntity<>(
                bloodBankStatisticsService.getAgeGroupAverageBMI(), HttpStatus.OK);
    }

    @GetMapping("/gender-obese-percentage")
    @Operation(summary = "Gender Obese Percentage", description = "Gender Obese Percentage")
    @ApiResponse(responseCode = "200", description = "OK",
            content = @Content(schema = @Schema(implementation = GenderObesePercentageDTO.class)))
    public ResponseEntity<List<GenderObesePercentageDTO>> getGenderObesePercentage() {
        return new ResponseEntity<>(
                bloodBankStatisticsService.getGenderObesePercentage(), HttpStatus.OK);
    }

    @GetMapping("/blood-type-average-age")
    @Operation(summary = "Blood Type Average Age", description = "Blood Type Average Age")
    @ApiResponse(responseCode = "200", description = "OK",
            content = @Content(schema = @Schema(implementation = BloodTypeAverageAgeDTO.class)))
    public ResponseEntity<List<BloodTypeAverageAgeDTO>> getBloodTypeAverageAge() {
        return new ResponseEntity<>(
                bloodBankStatisticsService.getBloodTypeAverageAge(), HttpStatus.OK);
    }

    @GetMapping("/blood-type-potential-donors")
    @Operation(summary = "Blood Type Potential Donors", description = "Blood Type Potential Donors")
    @ApiResponse(responseCode = "200", description = "OK",
            content = @Content(schema = @Schema(implementation = BloodTypePotentialDonorsDTO.class)))
    public ResponseEntity<List<BloodTypePotentialDonorsDTO>> getBloodTypePotentialDonors() {
        return new ResponseEntity<>(
                bloodBankStatisticsService.getBloodTypePotentialDonors(), HttpStatus.OK);
    }

}
