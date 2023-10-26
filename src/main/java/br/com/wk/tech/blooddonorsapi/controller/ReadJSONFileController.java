package br.com.wk.tech.blooddonorsapi.controller;

import br.com.wk.tech.blooddonorsapi.service.ReadJSONFileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@RestController
@RequestMapping("/v0/api/read-json")
@Validated
public class ReadJSONFileController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReadJSONFileController.class);

    private final ReadJSONFileService readJSONFileService;

    @Autowired
    public ReadJSONFileController(ReadJSONFileService readJSONFileService) {
        this.readJSONFileService = readJSONFileService;
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Read JSON PDF File", description = "Read JSON PDF File")
    @ApiResponse(responseCode = "200", description = "OK")
    public ResponseEntity<String> readJSONFile(@Valid @NotNull @RequestParam("json") final MultipartFile json) throws IOException, ParseException {
        LOGGER.info("ReadJSONFileController - readJSONFile()");
        LOGGER.info("ReadJSONFileController - readJSONFile() - json: " + json.getOriginalFilename());

        return new ResponseEntity<>(readJSONFileService.readJSONFile(json), HttpStatus.OK);
    }
}
