package br.com.wk.tech.blooddonorsapi.service.impl;

import br.com.wk.tech.blooddonorsapi.model.Donator;
import br.com.wk.tech.blooddonorsapi.repository.BloodTypeRepository;
import br.com.wk.tech.blooddonorsapi.repository.DonatorRepository;
import br.com.wk.tech.blooddonorsapi.service.ReadJSONFileService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Service
public class ReadJSONFileServiceImpl implements ReadJSONFileService {

    private final BloodTypeRepository bloodTypeRepository;

    private final DonatorRepository donatorRepository;

    @Autowired
    public ReadJSONFileServiceImpl(BloodTypeRepository bloodTypeRepository, DonatorRepository donatorRepository) {
        this.bloodTypeRepository = bloodTypeRepository;
        this.donatorRepository = donatorRepository;
    }

    @Override
    public String readJSONFile(FileReader file) throws IOException, ParseException {

        var jsonObjectArray = (JSONArray) new JSONParser().parse(file);

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
            donator.setEmail((String) jsonObject.get("email"));
            donator.setZipCode((String) jsonObject.get("cep"));
            donator.setAddress((String) jsonObject.get("endereco"));
            donator.setAddressNumber((Integer) jsonObject.get("numero"));
            donator.setNeighborhood((String) jsonObject.get("bairro"));
            donator.setCity((String) jsonObject.get("cidade"));
            donator.setState((String) jsonObject.get("estado"));
            donator.setPhoneNumber((String) jsonObject.get("telefone_fixo"));
            donator.setCelPhoneNumber((String) jsonObject.get("celular"));
            donator.setHeight((Double) jsonObject.get("altura"));
            donator.setWeight((Integer) jsonObject.get("peso"));
            donator.setBloodType(bloodTypeRepository.findByBloodType(
                    (String) jsonObject.get("tipo_sanguineo")));

            var savedDonator = donatorRepository.save(donator);

        }

        return "Sucess - JSON File Readed";
    }

    public LocalDateTime convertStringDateToLocalDateTime(String date) {
        var parser = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(date, parser).atStartOfDay();
    }

    private String handleUnnecessaryBar(String date) {
        return date.replace("\\", ".");
    }
}
