package com.romanov.service;

import com.romanov.model.staff.Consultant;
import com.romanov.model.staff.Practitioner;
import com.romanov.model.staff.Surgeon;
import com.romanov.repository.main.ConsultantRepository;
import com.romanov.repository.main.PractitionerRepository;
import com.romanov.repository.main.SurgeonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StaffService {

    private PractitionerRepository practitionerRepository;
    private SurgeonRepository surgeonRepository;
    private ConsultantRepository consultantRepository;

    @Autowired
    public StaffService(PractitionerRepository practitionerRepository,
                        SurgeonRepository surgeonRepository,
                        ConsultantRepository consultantRepository)
    {
        this.practitionerRepository = practitionerRepository;
        this.surgeonRepository = surgeonRepository;
        this.consultantRepository = consultantRepository;
    }

    public Practitioner savePractitioner(Practitioner practitioner)
    {
        return practitionerRepository.save(practitioner);
    }

    public Surgeon saveSurgeon(Surgeon surgeon)
    {
        return surgeonRepository.save(surgeon);
    }

    public Consultant saveConsultant(Consultant consultant)
    {
        return consultantRepository.save(consultant);
    }

}
