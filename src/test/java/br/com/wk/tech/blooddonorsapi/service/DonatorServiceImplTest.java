package br.com.wk.tech.blooddonorsapi;

import br.com.wk.tech.blooddonorsapi.dto.DonatorDTO;
import br.com.wk.tech.blooddonorsapi.model.Donator;
import br.com.wk.tech.blooddonorsapi.repository.DonatorRepository;
import br.com.wk.tech.blooddonorsapi.service.impl.DonatorServiceImpl;
import br.com.wk.tech.blooddonorsapi.utils.Utils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class DonatorServiceImplTest {

    @Mock
    private DonatorRepository donatorRepository;

    @InjectMocks
    private DonatorServiceImpl donatorService;

    private final Utils utils = new Utils();

    @Test
    public void should_returnDonatorDTO_when_saveBloodDonatorIsCalled() {
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

        var savedDonator = utils.dtoToEntity(donatorDTO);

        when(donatorRepository.save(any(Donator.class))).thenReturn(savedDonator);

        var donatorSucess = donatorService.saveBloodDonator(donatorDTO);

        assertEquals(donatorSucess.getId(), 1L);
        assertEquals(donatorSucess.getName(), "User Teste");
        assertEquals(donatorSucess.getBloodType(), "A+");

    }

    @Test
    public void should_returnDonatorDTO_when_updateBloodDonatorIsCalled() {
        var donatorDTO = new DonatorDTO();
        donatorDTO.setId(1L);
        donatorDTO.setName("User Teste Updated");
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

        var savedDonator = utils.dtoToEntity(donatorDTO);

        when(donatorRepository.saveAndFlush(any(Donator.class))).thenReturn(savedDonator);

        var donatorSucess = donatorService.updateBloodDonator(donatorDTO);

        assertEquals(donatorSucess.getId(), 1L);
        assertEquals(donatorSucess.getName(), "User Teste Updated");
        assertEquals(donatorSucess.getBloodType(), "A+");

    }

    @Test
    public void should_returnDonatorDTOList_when_called() {
        when(donatorRepository.findAll()).thenReturn(new ArrayList<>());
        var donatorDTOList = donatorService.getAllDonors();
        assertTrue(Objects.nonNull(donatorDTOList));
    }

    @Test
    public void should_returnDeletedDonatorId_when_donatorIsDeleted() {
        doNothing().when(donatorRepository).deleteById(anyLong());
        var deletedDonatorId = donatorService.deleteDonator(1L);
        assertTrue(Objects.nonNull(deletedDonatorId));
    }

    @Test
    public void should_returnDonatorDTO_when_called() {
        when(donatorRepository.findByDonatorId(1L)).thenReturn(new Donator());
        var donatorDTO = donatorService.getDonatorById(1L);
        assertTrue(Objects.nonNull(donatorDTO));
    }

}
