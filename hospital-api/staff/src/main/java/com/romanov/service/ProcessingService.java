package com.romanov.service;

import com.romanov.config.exception.UnprocessableException;
import com.romanov.feign.PatientFeignClient;
import com.romanov.model.client.Patient;
import com.romanov.model.record.MedicalHistory;
import com.romanov.model.record.MedicalRecord;
import com.romanov.model.record.MedicalRecordStatus;
import com.romanov.model.request.Request;
import com.romanov.model.request.RequestStatus;
import com.romanov.model.staff.Consultant;
import com.romanov.model.staff.Practitioner;
import com.romanov.model.staff.Surgeon;
import com.romanov.model.treatment.Medicine;
import com.romanov.model.treatment.Treatment;
import com.romanov.repository.main.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProcessingService {

    private RequestRepository requestRepository;
    private StaffService staffService;
    private TreatmentService treatmentService;

    private PatientFeignClient patientFeignClient;

    @Autowired
    public ProcessingService(RequestRepository requestRepository,
                             StaffService staffService,
                             TreatmentService treatmentService,
                             PatientFeignClient patientFeignClient)
    {
        this.requestRepository = requestRepository;
        this.staffService = staffService;
        this.treatmentService = treatmentService;
        this.patientFeignClient = patientFeignClient;
    }

    public List<Request> getRequestByStatus(RequestStatus requestStatus)
    {
        return requestRepository.findRequestsByStatus(requestStatus);
    }

    public Request confirmRequest(Request request)
    {
        request.setRequestStatus(RequestStatus.APPROVED);
        return requestRepository.save(request);
    }

    public Treatment createTreatment(Request request, Practitioner practitioner, Treatment treatmentRequest) throws UnprocessableException
    {
        Patient patient = request.getOwner();
        Treatment treatment = new Treatment(treatmentRequest.getStartDate(), treatmentRequest.getEndDate());

        practitioner.addTreatment(treatment);

        treatment.setTakenBy(patient);
        patient.setTreatment(treatment);

        //TODO more logic to select appropriate surgeons and consultants
        List<Surgeon> surgeons = staffService.getSurgeons();
        List<Consultant> consultants = staffService.getConsultants();

        Surgeon surgeon = surgeons.get(0);
        treatment.addSurgeon(surgeon);

        Consultant consultant = consultants.get(0);
        treatment.addConsultant(consultant);

        List<Medicine> medicines = treatmentRequest.getMedicines();

        treatment.addMedicines(medicines);

        treatment = treatmentService.saveTreatment(treatment);

        createMedicalRecord(treatment);

        return treatment;

    }

    public MedicalRecord createMedicalRecord(Treatment treatment)
    {
        MedicalRecord medicalRecord = new MedicalRecord(MedicalRecordStatus.ACTIVE);
        medicalRecord.setPractitioner(treatment.getMaker());
        medicalRecord.setStartDate(treatment.getStartDate());
        medicalRecord.setEndDate(treatment.getEndDate());

        Patient patient = treatment.getTakenBy();

        MedicalHistory medicalHistory = patient.getMedicalHistory();
        medicalHistory.addSpent(treatment.getTotalPrice());
        medicalHistory.incrementTreatmentCount();

        return patientFeignClient.addMedicalRecord(medicalHistory.getId(), medicalRecord);

    }

}
