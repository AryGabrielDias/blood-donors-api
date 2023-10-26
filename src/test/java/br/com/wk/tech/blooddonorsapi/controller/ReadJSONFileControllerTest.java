package br.com.wk.tech.blooddonorsapi.controller;

import br.com.wk.tech.blooddonorsapi.service.ReadJSONFileService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.File;
import java.io.FileInputStream;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ReadJSONFileController.class)
public class ReadJSONFileControllerTest {

    @MockBean
    private ReadJSONFileService readJSONFileService;
    private MockMvc mockMvc;
    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(
                new ReadJSONFileController(readJSONFileService)).build();
    }

    @Test
    public void should_returnString_when_readJSONFileIsCalled() throws Exception {

        var file = new File("C:\\KDI\\wk\\arquivo json - teste java.pdf");
        var input = new FileInputStream(file);

        var json = new MockMultipartFile("json", input);

        when(readJSONFileService.readJSONFile(any()))
                .thenReturn("");

        mockMvc.perform(MockMvcRequestBuilders.multipart("/v0/api/read-json")
                        .file(json)
                        .contentType(MediaType.MULTIPART_FORM_DATA_VALUE))
                .andExpect(status().isOk());
    }

}
