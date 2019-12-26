package com.romanov.test;

import com.romanov.controller.AdminPatientController;
import com.romanov.model.client.Patient;
import com.romanov.model.utils.Address;
import com.romanov.repository.main.PatientRepository;
import com.romanov.service.AdminPatientService;
import com.romanov.util.Funcs;
import com.romanov.validator.PatientValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.validation.Validator;
import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
@WebAppConfiguration
public class PatientTest {

    @Autowired
    private PatientRepository patientRepository;

    @Mock
    private PatientRepository mockPatientRepository;

    @Autowired
    private PatientValidator patientValidator;

    @Autowired
    private MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter;

    private AdminPatientController adminPatientController;
    private AdminPatientService adminPatientService;

    private MockMvc mockmvc;

    private Validator validator;

    @PersistenceContext
    private EntityManager entityManager;

    private Patient patientBadParam;
    private Patient patientValid;

    @BeforeEach
    public void setUp() throws Exception
    {
        patientValid = new Patient("firstName", "lastName", 16, "email@gmail.com", "1234567890");

        adminPatientService = new AdminPatientService(mockPatientRepository);
        adminPatientController = new AdminPatientController(adminPatientService, patientValidator);
        mockmvc = standaloneSetup(adminPatientController).setMessageConverters(mappingJackson2HttpMessageConverter).setValidator(patientValidator).build();
    }

    @Test
    public void savePatientInvalidParam() throws Exception
    {
        patientBadParam = new Patient("firstName", "lastName", 10, "email@gmail.com", "1");

        String errorMessage = mockmvc
                .perform(
                    post("/admin/patient")
                        .contentType("application/json")
                        .content(Funcs.objectToJson(patientBadParam))
                )
                .andExpect(status().isBadRequest())
                .andReturn().getResolvedException().getMessage();

        verify(mockPatientRepository, times(0)).save(any());
        Assertions.assertTrue(errorMessage.contains("INVALID_AGE"));
        Assertions.assertTrue(errorMessage.contains("INVALID_PHONE"));
    }

    @Test
    public void uniquePatientEmail()
    {
        Assertions.assertThrows(PersistenceException.class, () ->
        {
            Patient patient = new Patient("", "", 16, "email", "");
            Patient patient2 = new Patient("1", "3", 16, "email", "3");

            patientRepository.save(patient);
            patientRepository.save(patient2);

            entityManager.flush();
        });
    }

    @Test
    public void savePatientOk() throws Exception
    {
        Address addressValid = new Address("line1", "line2", "town","postcode", "country");
        patientValid.setAddresses(Arrays.asList(addressValid));

        when(mockPatientRepository.save(patientValid)).thenReturn(patientValid);

        ResultActions resultActions = mockmvc
                .perform(
                        post("/admin/patient")
                                .contentType("application/json")
                                .content(Funcs.objectToJson(patientValid))
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$['first_name']").value(patientValid.getFirstName()))
                .andExpect(jsonPath("$['addresses'][0]['address_line1']").value(addressValid.getAddressLine1()));

        verify(mockPatientRepository, times(1)).save(any());
    }

    @Test
    public void savePatientAddressBadParam() throws Exception
    {
        Address addressBadParam = new Address("line1", "line2", "town","code", "country");
        patientValid.setAddresses(Arrays.asList(addressBadParam));

        String errorMessage = mockmvc
                .perform(
                        post("/admin/patient")
                                .contentType("application/json")
                                .content(Funcs.objectToJson(patientValid))
                )
                .andExpect(status().isBadRequest())
                .andReturn().getResolvedException().getMessage();

        verify(mockPatientRepository, times(0)).save(any());
        Assertions.assertTrue(errorMessage.contains("INVALID_POSTCODE"));
    }


}
