package br.com.wk.tech.blooddonorsapi.service;

import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public interface ReadJSONFileService {

    String readJSONFile(FileReader file) throws IOException, ParseException;
}
