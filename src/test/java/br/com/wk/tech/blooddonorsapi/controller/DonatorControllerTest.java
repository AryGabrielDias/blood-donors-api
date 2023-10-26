package br.com.wk.tech.blooddonorsapi.controller;

import br.com.wk.tech.blooddonorsapi.dto.DonatorDTO;
import br.com.wk.tech.blooddonorsapi.service.DonatorService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = DonatorController.class)
public class DonatorControllerTest {

    @MockBean
    private DonatorService donatorService;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders
                .standaloneSetup(new DonatorController(donatorService)).build();
    }

    @Test
    public void should_returnDonatorDTO_when_saveBloodDonatorIsCalled() throws Exception {

        ObjectMapper mapper = new ObjectMapper();

        var donatorDTO = new DonatorDTO();
        donatorDTO.setId(1L);
        donatorDTO.setName("User Teste");
        donatorDTO.setCpf("001.001.001-50");
        donatorDTO.setRg("01.001.001-5");
        donatorDTO.setBirthDate("23/05/1964");
        donatorDTO.setGender("Masculino");
        donatorDTO.setMothersName("Mae do User Teste");
        donatorDTO.setFathersName("Pai do User Teste");
        donatorDTO.setEmail("userteste@email.com");
        donatorDTO.setZipCode("00101-501");
        donatorDTO.setAddress("Quadra do User Teste");
        donatorDTO.setAddressNumber(10);
        donatorDTO.setNeighborhood("Riacho Fundo");
        donatorDTO.setCity("Brasilia");
        donatorDTO.setState("DF");
        donatorDTO.setPhoneNumber("(61) 9988-9988");
        donatorDTO.setCelPhoneNumber("(61) 99988-9988");
        donatorDTO.setHeight(1.75);
        donatorDTO.setWeight(90);
        donatorDTO.setBloodType("A+");

        when(donatorService.saveBloodDonator(any())).thenReturn(new DonatorDTO());

        mockMvc.perform(post("/v0/api/donator")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(donatorDTO)))
                .andExpect(status().isCreated());
    }

    @Test
    public void should_returnDonatorDTO_when_updateBloodDonatorIsCalled() throws Exception {

        ObjectMapper mapper = new ObjectMapper();

        var donatorDTO = new DonatorDTO();
        donatorDTO.setId(1L);
        donatorDTO.setName("User Teste");
        donatorDTO.setCpf("001.001.001-50");
        donatorDTO.setRg("01.001.001-5");
        donatorDTO.setBirthDate("23/05/1964");
        donatorDTO.setGender("Masculino");
        donatorDTO.setMothersName("Mae do User Teste");
        donatorDTO.setFathersName("Pai do User Teste");
        donatorDTO.setEmail("userteste@email.com");
        donatorDTO.setZipCode("00101-501");
        donatorDTO.setAddress("Quadra do User Teste");
        donatorDTO.setAddressNumber(10);
        donatorDTO.setNeighborhood("Riacho Fundo");
        donatorDTO.setCity("Brasilia");
        donatorDTO.setState("DF");
        donatorDTO.setPhoneNumber("(61) 9988-9988");
        donatorDTO.setCelPhoneNumber("(61) 99988-9988");
        donatorDTO.setHeight(1.75);
        donatorDTO.setWeight(90);
        donatorDTO.setBloodType("A+");

        when(donatorService.updateBloodDonator(any())).thenReturn(new DonatorDTO());

        mockMvc.perform(put("/v0/api/donator")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(donatorDTO)))
                .andExpect(status().isOk());
    }

    @Test
    public void should_returnListDonatorDTO_when_getAllDonorsIsCalled() throws Exception {
        when(donatorService.getAllDonors()).thenReturn(new ArrayList<>());
        mockMvc.perform(get("/v0/api/donator")).andExpect(status().isOk());
    }

    @Test
    public void should_returnDonatorDTO_when_getBloodDonatorByIdIsCalled() throws Exception {
        when(donatorService.getDonatorById(any())).thenReturn(new DonatorDTO());
        mockMvc.perform(get("/v0/api/donator/1")).andExpect(status().isOk());
    }

    @Test
    public void should_returnDeletedDonatorId_when_deleteBloodDonatorIsCalled() throws Exception {
        when(donatorService.deleteDonator(any())).thenReturn(1L);
        mockMvc.perform(delete("/v0/api/donator/1")).andExpect(status().isOk());
    }

}
