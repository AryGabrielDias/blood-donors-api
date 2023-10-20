package br.com.wk.tech.blooddonorsapi.controller;

import br.com.wk.tech.blooddonorsapi.dto.DonatorDTO;
import br.com.wk.tech.blooddonorsapi.service.DonatorService;
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
    public ResponseEntity<List<DonatorDTO>> getAllDonors() {
        return new ResponseEntity<>(donatorService.getAllDonors(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<DonatorDTO> saveBloodDonator(@RequestBody  DonatorDTO donatorDTO) {
        return new ResponseEntity<>(donatorService.saveBloodDonator(donatorDTO), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<DonatorDTO> updateBloodDonator(@RequestBody  DonatorDTO donatorDTO) {
        return new ResponseEntity<>(donatorService.updateBloodDonator(donatorDTO), HttpStatus.OK);
    }

    @GetMapping(("/{donatorId}"))
    public ResponseEntity<DonatorDTO> getBloodDonatorById(@PathVariable("donatorId") Long donatorId) {
        return new ResponseEntity<>(donatorService.getDonatorById(donatorId), HttpStatus.OK);
    }

    @DeleteMapping("/{donatorId}")
    public ResponseEntity<Long> deleteBloodDonator(@PathVariable("donatorId") Long donatorId) {
        return new ResponseEntity<>(donatorService.deleteDonator(donatorId), HttpStatus.OK);
    }
}
