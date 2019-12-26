package com.romanov.controller;

import com.romanov.model.staff.Consultant;
import com.romanov.model.staff.Practitioner;
import com.romanov.model.staff.Surgeon;
import com.romanov.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/staff")
public class StaffController {

    private StaffService staffService;

    @Autowired
    public StaffController(StaffService staffService)
    {
        this.staffService = staffService;
    }

    @PostMapping("/practitioner")
    public Practitioner savePractitioner(@RequestBody Practitioner practitioner)
    {
        return staffService.savePractitioner(practitioner);
    }

    @PostMapping("/surgeon")
    public Surgeon saveSurgeon(@RequestBody Surgeon surgeon)
    {
        return staffService.saveSurgeon(surgeon);
    }

    @PostMapping("/consultant")
    public Consultant saveConsultant(@RequestBody Consultant consultant)
    {
        return staffService.saveConsultant(consultant);
    }

}
