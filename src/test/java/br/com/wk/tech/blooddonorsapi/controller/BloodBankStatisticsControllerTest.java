package br.com.wk.tech.blooddonorsapi.controller;

import br.com.wk.tech.blooddonorsapi.service.BloodBankStatisticsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = BloodBankStatisticsController.class)
public class BloodBankStatisticsControllerTest {

    @MockBean
    private BloodBankStatisticsService bloodBankStatisticsService;

    private MockMvc mockMvc;
    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(
                new BloodBankStatisticsController(bloodBankStatisticsService)).build();
    }

    @Test
    public void should_returnListDonorsPerStateDTO_when_getNumberOfDonorsPerStateIsCalled() throws Exception {
        when(bloodBankStatisticsService.getNumberOfDonorsPerState()).thenReturn(new ArrayList<>());
        mockMvc.perform(get("/v0/api/bank-statistics/donors-per-state"))
                .andExpect(status().isOk());
    }

    @Test
    public void should_returnListAgeGroupAverageBMIDTO_when_getAgeGroupAverageBMIIsCalled() throws Exception {
        when(bloodBankStatisticsService.getAgeGroupAverageBMI()).thenReturn(new ArrayList<>());
        mockMvc.perform(get("/v0/api/bank-statistics/age-group-average-bmi"))
                .andExpect(status().isOk());
    }

    @Test
    public void should_returnListGenderObesePercentageDTO_when_getGenderObesePercentageIsCalled() throws Exception {
        when(bloodBankStatisticsService.getGenderObesePercentage()).thenReturn(new ArrayList<>());
        mockMvc.perform(get("/v0/api/bank-statistics/gender-obese-percentage"))
                .andExpect(status().isOk());
    }

    @Test
    public void should_returnListBloodTypeAverageAgeDTO_when_getBloodTypeAverageAgeIsCalled() throws Exception {
        when(bloodBankStatisticsService.getBloodTypeAverageAge()).thenReturn(new ArrayList<>());
        mockMvc.perform(get("/v0/api/bank-statistics/blood-type-average-age"))
                .andExpect(status().isOk());
    }

    @Test
    public void should_returnListBloodTypePotentialDonorsDTO_when_getBloodTypePotentialDonorsIsCalled() throws Exception {
        when(bloodBankStatisticsService.getBloodTypePotentialDonors()).thenReturn(new ArrayList<>());
        mockMvc.perform(get("/v0/api/bank-statistics/blood-type-potential-donors"))
                .andExpect(status().isOk());
    }
}
