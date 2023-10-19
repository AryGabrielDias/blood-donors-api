package br.com.wk.tech.blooddonorsapi.controller;

import br.com.wk.tech.blooddonorsapi.service.ReadJSONFileService;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileReader;
import java.io.IOException;

@RestController
@RequestMapping("/v0/api/read-json")
public class ReadJSONFileController {

    private final ReadJSONFileService readJSONFileService;

    @Autowired
    public ReadJSONFileController(ReadJSONFileService readJSONFileService) {
        this.readJSONFileService = readJSONFileService;
    }

    @PostMapping
    public ResponseEntity<String> readJSONFile(@RequestParam("json") MultipartFile json) throws IOException, ParseException {
        return new ResponseEntity<>(readJSONFileService.readJSONFile(
                new FileReader(String.valueOf(json.getInputStream()))), HttpStatus.OK);
    }
}
