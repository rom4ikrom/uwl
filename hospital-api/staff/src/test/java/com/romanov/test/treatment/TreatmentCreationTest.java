package com.romanov.test.treatment;

import com.romanov.config.exception.NotFoundException;
import com.romanov.config.exception.UnprocessableException;
import com.romanov.controller.ProcessingController;
import com.romanov.controller.StaffController;
import com.romanov.model.client.Patient;
import com.romanov.model.request.Request;
import com.romanov.model.request.RequestStatus;
import com.romanov.model.request.RequestType;
import com.romanov.model.staff.Consultant;
import com.romanov.model.staff.Practitioner;
import com.romanov.model.staff.Surgeon;
import com.romanov.model.treatment.Treatment;
import com.romanov.model.utils.PersonRole;
import com.romanov.repository.main.ConsultantRepository;
import com.romanov.repository.main.PractitionerRepository;
import com.romanov.repository.main.SurgeonRepository;
import com.romanov.repository.main.TreatmentRepository;
import com.romanov.service.RequestService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.List;

@ActiveProfiles("test")
@SpringBootTest
@RunWith(SpringRunner.class)
@WebAppConfiguration
@Transactional("testTransactionManager")
public class TreatmentCreationTest {

    @Autowired
    private SurgeonRepository surgeonRepository;

    @Autowired
    private PractitionerRepository practitionerRepository;

    @Autowired
    private ConsultantRepository consultantRepository;

    @Autowired
    private TreatmentRepository treatmentRepository;

    @Autowired
    private RequestService requestService;

    @Autowired
    private StaffController staffController;

    @Autowired
    private ProcessingController processingController;

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void saveStaffTest() throws Exception
    {
        saveHospitalStaff();

        Assertions.assertNotNull(practitionerRepository.findByEmail("practitioner@gmail.com"));
        Assertions.assertNotNull(surgeonRepository.findByEmail("surgeon@gmail.com"));
        Assertions.assertNotNull(consultantRepository.findByEmail("consultant@gmail.com"));
    }

    @Test
    public void confirmRequestTest() throws Exception
    {
        saveHospitalStaff();

        Request confirmedRequest = confirmRequest();

        Assertions.assertEquals(confirmedRequest.getRequestStatus(), RequestStatus.APPROVED);
    }

    @Test
    public void createTreatmentRequest() throws Exception
    {
        saveHospitalStaff();
        Request confirmedRequest = confirmRequest();
        Practitioner practitioner = practitionerRepository.findByEmail("practitioner@gmail.com");
        Treatment treatment = new Treatment(new Date(1575158400), new Date(1575244800));

        Treatment savedTreatment = processingController.createTreatment(confirmedRequest.getId(), practitioner.getId(), treatment);

        Assertions.assertFalse(practitionerRepository.findByEmail("practitioner@gmail.com").getRequests().isEmpty());
        Assertions.assertFalse(savedTreatment.getConsultants().isEmpty());
        Assertions.assertFalse(savedTreatment.getSurgeons().isEmpty());
    }

    private void saveHospitalStaff()
    {
        Surgeon surgeon = new Surgeon("Surgeon", "1", 35, "surgeon@gmail.com", "12345678900", PersonRole.SURGEON);
        staffController.saveSurgeon(surgeon);

        Practitioner practitioner = new Practitioner("Practitioner", "1", 35, "practitioner@gmail.com", "12345678901", PersonRole.PRACTITIONER);
        staffController.savePractitioner(practitioner);

        Consultant consultant = new Consultant("Consultant", "1", 35, "consultant@gmail.com", "12345678902", PersonRole.CONSULTANT);
        staffController.saveConsultant(consultant);
    }

    private Request confirmRequest() throws NotFoundException, UnprocessableException
    {
        Request request = new Request(RequestType.TREATMENT);

        Patient patient = new Patient("firstName", "lastName", 16, "email@gmail.com", "1234567890");

        List<Request> requestList = requestService.createRequest(patient, request);

        return processingController.confirmRequest(requestList.get(requestList.size() - 1).getId(), practitionerRepository.findByEmail("practitioner@gmail.com").getId());
    }
}
