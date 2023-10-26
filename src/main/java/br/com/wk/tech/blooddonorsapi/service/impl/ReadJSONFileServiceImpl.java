package br.com.wk.tech.blooddonorsapi.service.impl;

import br.com.wk.tech.blooddonorsapi.model.Donator;
import br.com.wk.tech.blooddonorsapi.repository.DonatorRepository;
import br.com.wk.tech.blooddonorsapi.service.ReadJSONFileService;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Objects;

@Service
public class ReadJSONFileServiceImpl implements ReadJSONFileService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReadJSONFileServiceImpl.class);

    private final DonatorRepository donatorRepository;

    @Autowired
    public ReadJSONFileServiceImpl(DonatorRepository donatorRepository) {
        this.donatorRepository = donatorRepository;
    }

    @Override
    public String readJSONFile(final MultipartFile json) throws IOException, ParseException {

        LOGGER.info("ReadJSONFileServiceImpl - readJSONFile()");

        var jsonString = "";

        try (final PDDocument document = PDDocument.load(json.getInputStream())) {
            final PDFTextStripper pdfTextStripper = new PDFTextStripper();
            jsonString = pdfTextStripper.getText(document);

        }
        catch (final Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error("ERROR Parsing PDF");
        }

        var jsonObjectArray = (JSONArray) new JSONParser().parse(jsonString);

        var donorsList = new ArrayList<Donator>();

        for (Object obj : jsonObjectArray) {

            var jsonObject = (JSONObject) obj;

            var donator = new Donator();
            donator.setId(1535L);
            donator.setName((String) jsonObject.get("nome"));
            donator.setCpf((String) jsonObject.get("cpf"));
            donator.setRg((String) jsonObject.get("rg"));
            donator.setBirthDate(this.convertStringDateToLocalDateTime(this
                    .handleUnnecessaryBar((String) jsonObject.get("data_nasc"))));
            donator.setGender((String) jsonObject.get("sexo"));
            donator.setMothersName((String) jsonObject.get("mae"));
            donator.setFathersName((String) jsonObject.get("pai"));
            donator.setEmail(Objects.isNull(jsonObject.get("email")) ? "email@email.com" :
                    (String) jsonObject.get("email"));
            donator.setZipCode((String) jsonObject.get("cep"));
            donator.setAddress((String) jsonObject.get("endereco"));
            donator.setAddressNumber(((Long) jsonObject.get("numero")).intValue());
            donator.setNeighborhood((String) jsonObject.get("bairro"));
            donator.setCity((String) jsonObject.get("cidade"));
            donator.setState((String) jsonObject.get("estado"));
            donator.setPhoneNumber((String) jsonObject.get("telefone_fixo"));
            donator.setCelPhoneNumber((String) jsonObject.get("celular"));
            donator.setHeight(String.valueOf(jsonObject.get("altura")));
            donator.setWeight(((Long) jsonObject.get("peso")).intValue());
            donator.setBloodType((String) jsonObject.get("tipo_sanguineo"));

            donorsList.add(donatorRepository.save(donator));

        }

        var jsonRead = "Success - JSON File Read" + donorsList.size() + " Registers";

        LOGGER.info("ReadJSONFileServiceImpl - readJSONFile() - Success - JSON File Read");

        return jsonRead;
    }

    public LocalDateTime convertStringDateToLocalDateTime(String date) {
        var parser = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(date, parser).atStartOfDay();
    }

    private String handleUnnecessaryBar(String date) {
        return date.replace("\\", ".");
    }
}
