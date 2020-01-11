package com.romanov.test.treatment;

import com.romanov.config.exception.UnprocessableException;
import com.romanov.model.staff.Consultant;
import com.romanov.model.staff.Surgeon;
import com.romanov.model.treatment.Treatment;
import com.romanov.model.utils.PersonRole;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
@WebAppConfiguration
public class TreatmentTest {

    private Treatment treatment;

    @BeforeEach
    public void setUp() throws Exception
    {
        Surgeon surgeon1 = new Surgeon("Surgeon", "1", 35, "surgeon1@gmail.com", "12345678900", PersonRole.SURGEON);
        Surgeon surgeon2 = new Surgeon("Surgeon", "2", 30, "surgeon2@gmail.com", "12345678901", PersonRole.SURGEON);
        Surgeon surgeon3 = new Surgeon("Surgeon", "3", 35, "surgeon3@gmail.com", "12345678902", PersonRole.SURGEON);

        List<Surgeon> surgeonList = new ArrayList<>();
        surgeonList.add(surgeon1);
        surgeonList.add(surgeon2);
        surgeonList.add(surgeon3);

        Consultant consultant1 = new Consultant("Consultant", "1", 35, "consultant1@gmail.com", "12345678905", PersonRole.CONSULTANT);

        List<Consultant> consultantList = new ArrayList<>();
        consultantList.add(consultant1);

        treatment = new Treatment(new Date(1575158400),new Date(1575244800));
        treatment.addSurgeons(surgeonList);
        treatment.addConsultants(consultantList);
    }

    @Test
    public void treatmentSurgeonsConstraints() throws Exception
    {
        Assertions.assertThrows(UnprocessableException.class, () -> {

            Surgeon surgeon = new Surgeon("Surgeon", "extra", 35, "surgeonExtra@gmail.com", "12345678904", PersonRole.SURGEON);
            treatment.addSurgeon(surgeon);
        });
    }

    @Test
    public void treatmentConsultatnConstraints() throws Exception
    {
        {
            Assertions.assertThrows(UnprocessableException.class, () -> {

                Consultant consultant = new Consultant("Consultant", "extra", 30, "consultantExtra@gmail.com", "12345678906", PersonRole.CONSULTANT);
                treatment.addConsultant(consultant);
            });
        }
    }
}
