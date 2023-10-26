package br.com.wk.tech.blooddonorsapi.controller;

import br.com.wk.tech.blooddonorsapi.dto.DonatorDTO;
import br.com.wk.tech.blooddonorsapi.service.DonatorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v0/api/donator")
public class DonatorController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DonatorController.class);

    private final DonatorService donatorService;

    @Autowired
    public DonatorController(DonatorService donatorService) {
        this.donatorService = donatorService;
    }

    @GetMapping
    @Operation(summary = "Get All Donors", description = "Get All Donors")
    @ApiResponse(responseCode = "200", description = "OK",
            content = @Content(schema = @Schema(implementation = DonatorDTO.class)))
    public ResponseEntity<List<DonatorDTO>> getAllDonors() {
        return new ResponseEntity<>(donatorService.getAllDonors(), HttpStatus.OK);
    }

    @PostMapping
    @Operation(summary = "Save Blood Donor", description = "Save Blood Donor")
    @ApiResponse(responseCode = "200", description = "OK",
            content = @Content(schema = @Schema(implementation = DonatorDTO.class)))
    public ResponseEntity<DonatorDTO> saveBloodDonator(@RequestBody  DonatorDTO donatorDTO) {
        return new ResponseEntity<>(donatorService.saveBloodDonator(donatorDTO), HttpStatus.CREATED);
    }

    @PutMapping
    @Operation(summary = "Update Blood Donor", description = "Update Blood Donor")
    @ApiResponse(responseCode = "200", description = "OK",
            content = @Content(schema = @Schema(implementation = DonatorDTO.class)))
    public ResponseEntity<DonatorDTO> updateBloodDonator(@RequestBody  DonatorDTO donatorDTO) {
        return new ResponseEntity<>(donatorService.updateBloodDonator(donatorDTO), HttpStatus.OK);
    }

    @GetMapping(("/{donatorId}"))
    @Operation(summary = "Get Blood Donor By ID", description = "Get Blood Donor By ID")
    @ApiResponse(responseCode = "200", description = "OK",
            content = @Content(schema = @Schema(implementation = DonatorDTO.class)))
    public ResponseEntity<DonatorDTO> getBloodDonatorById(@PathVariable("donatorId") Long donatorId) {
        return new ResponseEntity<>(donatorService.getDonatorById(donatorId), HttpStatus.OK);
    }

    @DeleteMapping("/{donatorId}")
    @Operation(summary = "Delete Donor By ID", description = "Delete Donor By ID")
    @ApiResponse(responseCode = "200", description = "OK")
    public ResponseEntity<Long> deleteBloodDonator(@PathVariable("donatorId") Long donatorId) {
        return new ResponseEntity<>(donatorService.deleteDonator(donatorId), HttpStatus.OK);
    }
}
