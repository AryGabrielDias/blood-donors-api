package br.com.wk.tech.blooddonorsapi.service;

import org.json.simple.parser.ParseException;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileReader;
import java.io.IOException;

public interface ReadJSONFileService {

    String readJSONFile(final MultipartFile json) throws IOException, ParseException;
}
