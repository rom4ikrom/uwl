package com.romanov.controller;

import com.romanov.model.staff.Consultant;
import com.romanov.model.staff.Practitioner;
import com.romanov.model.staff.Surgeon;
import com.romanov.service.StaffService;
import com.romanov.validator.ConsultantValidator;
import com.romanov.validator.PractitionerValidator;
import com.romanov.validator.SurgeonValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/admin/staff")
public class StaffController {

    private StaffService staffService;

    private PractitionerValidator practitionerValidator;
    private SurgeonValidator surgeonValidator;
    private ConsultantValidator consultantValidator;

    @InitBinder("practitioner")
    public void initBinderPractitioner(WebDataBinder webDataBinder)
    {
        webDataBinder.setValidator(practitionerValidator);
    }

    @InitBinder("surgeon")
    public void initBinderSurgeon(WebDataBinder webDataBinder)
    {
        webDataBinder.setValidator(surgeonValidator);
    }

    @InitBinder("consultant")
    public void initBinderConsultant(WebDataBinder webDataBinder)
    {
        webDataBinder.setValidator(consultantValidator);
    }

    @Autowired
    public StaffController(StaffService staffService,
                           PractitionerValidator practitionerValidator,
                           SurgeonValidator surgeonValidator,
                           ConsultantValidator consultantValidator)
    {
        this.staffService = staffService;
        this.practitionerValidator = practitionerValidator;
        this.surgeonValidator = surgeonValidator;
        this.consultantValidator = consultantValidator;
    }

    @PostMapping("/practitioner")
    public Practitioner savePractitioner(@Valid @RequestBody Practitioner practitionerRequest)
    {
        return staffService.createPractitioner(practitionerRequest);
    }

    @PostMapping("/surgeon")
    public Surgeon saveSurgeon(@Valid @RequestBody Surgeon surgeon)
    {
        return staffService.saveSurgeon(surgeon);
    }

    @PostMapping("/consultant")
    public Consultant saveConsultant(@Valid @RequestBody Consultant consultant)
    {
        return staffService.saveConsultant(consultant);
    }

}
