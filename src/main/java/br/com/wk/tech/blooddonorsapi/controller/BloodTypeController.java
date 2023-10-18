package br.com.wk.tech.blooddonorsapi.controller;

import br.com.wk.tech.blooddonorsapi.dto.BloodTypeDTO;
import br.com.wk.tech.blooddonorsapi.service.BloodTypeService;
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
@RequestMapping("/v0/api/blood-type")
public class BloodTypeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BloodTypeController.class);

    private final BloodTypeService bloodTypeService;

    @Autowired
    public BloodTypeController(BloodTypeService bloodTypeService) {
        this.bloodTypeService = bloodTypeService;
    }

    @GetMapping
    public ResponseEntity<List<BloodTypeDTO>> getAllBloodTypes() {
        return new ResponseEntity<>(bloodTypeService.getAllBloodTypes(), HttpStatus.OK);
    }
}
