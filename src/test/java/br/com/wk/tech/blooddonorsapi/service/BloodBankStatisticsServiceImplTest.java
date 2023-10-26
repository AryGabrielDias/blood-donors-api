package br.com.wk.tech.blooddonorsapi.service;

import br.com.wk.tech.blooddonorsapi.repository.DonatorRepository;
import br.com.wk.tech.blooddonorsapi.service.impl.BloodBankStatisticsServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BloodBankStatisticsServiceImplTest {

    @Mock
    private DonatorRepository donatorRepository;

    @InjectMocks
    private BloodBankStatisticsServiceImpl bloodBankStatisticsService;

    @BeforeEach
    public void setUp() {
        when(donatorRepository.findAll()).thenReturn(new ArrayList<>());
    }

    @Test
    public void should_returnListDonorsPerStateDTO_when_getNumberOfDonorsPerStateIsCalled() {
        assertTrue(Objects.nonNull(bloodBankStatisticsService.getNumberOfDonorsPerState()));
    }

    @Test
    public void should_returnListAgeGroupAverageBMIDTO_when_getAgeGroupAverageBMIIsCalled() {
        assertTrue(Objects.nonNull(bloodBankStatisticsService.getAgeGroupAverageBMI()));
    }

    @Test
    public void should_returnListGenderObesePercentageDTO_when_getGenderObesePercentageIsCalled() {
        assertTrue(Objects.nonNull(bloodBankStatisticsService.getGenderObesePercentage()));
    }

    @Test
    public void should_returnListBloodTypeAverageAgeDTO_when_getBloodTypeAverageAgeIsCalled() {
        assertTrue(Objects.nonNull(bloodBankStatisticsService.getBloodTypeAverageAge()));
    }

    @Test
    public void should_returnListBloodTypePotentialDonorsDTO_when_getBloodTypePotentialDonorsIsCalled() {
        assertTrue(Objects.nonNull(bloodBankStatisticsService.getBloodTypePotentialDonors()));
    }
}
