package br.com.wk.tech.blooddonorsapi.service;

import br.com.wk.tech.blooddonorsapi.model.Donator;
import br.com.wk.tech.blooddonorsapi.repository.DonatorRepository;
import br.com.wk.tech.blooddonorsapi.service.impl.ReadJSONFileServiceImpl;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.lenient;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class ReadJSONFileServiceImplTest {

    @Mock
    private DonatorRepository donatorRepository;

    @InjectMocks
    private ReadJSONFileServiceImpl readJSONFileService;

    @Test
    public void should_returnSucess_when_readJSONFileIsCalled() throws IOException, ParseException {
        var file = new File("C:\\KDI\\wk\\arquivo json - teste java.pdf");
        var input = new FileInputStream(file);

        var json = new MockMultipartFile("json", input);

        lenient().when(donatorRepository.save(any())).thenReturn(new Donator());

        assertTrue(Objects.nonNull(readJSONFileService.readJSONFile(json)));

    }

    @Test
    public void should_throwException_when_readJSONFileIsCalled() throws IOException, ParseException {
        MockMultipartFile pdfJsonList = new MockMultipartFile(
                "file", "pdfJsonList.pdf",
                MediaType.MULTIPART_FORM_DATA_VALUE,
                ("Teste").getBytes()
        );

        lenient().when(donatorRepository.save(any())).thenReturn(new Donator());

        assertThrows(Exception.class, () ->
                readJSONFileService.readJSONFile(pdfJsonList));

    }
}
