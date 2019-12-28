package com.romanov.service;

import com.romanov.config.exception.UnprocessableException;
import com.romanov.feign.PatientFeignClient;
import com.romanov.feign.PatientRemoteClient;
import com.romanov.model.client.Patient;
import com.romanov.model.record.MedicalHistory;
import com.romanov.model.record.MedicalRecord;
import com.romanov.model.request.Request;
import com.romanov.model.staff.Consultant;
import com.romanov.model.staff.Practitioner;
import com.romanov.model.staff.Surgeon;
import com.romanov.model.treatment.Medicine;
import com.romanov.model.treatment.Treatment;
import com.romanov.repository.main.TreatmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TreatmentService {

    private TreatmentRepository treatmentRepository;
    private StaffService staffService;
    private PatientRemoteClient patientRemoteClient;
    private HistoryService historyService;

    @Autowired
    public TreatmentService(TreatmentRepository treatmentRepository,
                            StaffService staffService,
                            PatientRemoteClient patientRemoteClient,
                            HistoryService historyService)
    {
        this.treatmentRepository = treatmentRepository;
        this.staffService = staffService;
        this.patientRemoteClient = patientRemoteClient;
        this.historyService = historyService;
    }

    public Treatment saveTreatment(Treatment treatment)
    {
        return treatmentRepository.save(treatment);
    }

    public Treatment createTreatment(Treatment treatmentRequest) throws UnprocessableException
    {
        Treatment treatment = new Treatment(treatmentRequest.getStartDate(), treatmentRequest.getEndDate());

        //TODO more logic to select appropriate surgeons and consultants
        List<Surgeon> surgeons = staffService.getSurgeons();
        List<Consultant> consultants = staffService.getConsultants();

        Surgeon surgeon = surgeons.get(0);
        treatment.addSurgeon(surgeon);

        Consultant consultant = consultants.get(0);
        treatment.addConsultant(consultant);

        List<Medicine> medicines = treatmentRequest.getMedicines();

        treatment.addMedicines(medicines);

        treatment = saveTreatment(treatment);

        return treatment;

    }

    public Treatment createTreatmentMedicalRecord(Request request, Practitioner practitioner, Treatment treatmentRequest) throws UnprocessableException
    {
        Patient patient = request.getOwner();

        MedicalRecord medicalRecord = new MedicalRecord(request.getRequestType());

        Treatment treatment = createTreatment(treatmentRequest);

        medicalRecord.setStartDate(treatment.getStartDate());
        medicalRecord.setEndDate(treatment.getEndDate());
        medicalRecord.setServiceId(treatment.getId());
        medicalRecord.setRequestId(request.getId());
        medicalRecord.setTotalPrice(treatment.getTotalPrice());

        practitioner.addMedicalRecord(medicalRecord);
        Practitioner updatedPractitioner = staffService.savePractitioner(practitioner);

        List<MedicalRecord> medicalRecords = updatedPractitioner.getMedicalRecords();

        medicalRecord = medicalRecords.get(medicalRecords.size() - 1);

        historyService.addMedicalRecord(patient.getMedicalHistory(), medicalRecord);

        return treatment;

    }



}
