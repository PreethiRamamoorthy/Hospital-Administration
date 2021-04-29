package org.launchcode.hospitaladministration.controllers;

import org.launchcode.hospitaladministration.data.PatientsRepository;
import org.launchcode.hospitaladministration.models.Doctors;
import org.launchcode.hospitaladministration.models.Patients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.Optional;

@Controller
@RequestMapping("patients")
public class PatientsController {

    @Autowired
    private PatientsRepository patientsRepository;

    @GetMapping()
    public String displayAllPatients(Model model){
        model.addAttribute("title","All Patients");
        model.addAttribute("patients",patientsRepository.findAll());
        return "patients/index";
    }

    @GetMapping("create")
    public String renderCreatePatientsForm(Model model){
        model.addAttribute("title","Add a Patient");
        model.addAttribute(new Patients());
        return "patients/create";
    }

    @PostMapping("create")
    public String processCreatePatientsForm(@ModelAttribute @Valid Patients patients, Errors errors, Model model){
        if (errors.hasErrors()) {
            model.addAttribute("title", "Add a Patient");
            model.addAttribute(new Patients());
            return "patients/create";
        }
        patientsRepository.save(patients);
        return "redirect:";
    }

    @GetMapping("delete/{patientId}")
    public String displayDeleteForm(Model model,@PathVariable int patientId) {
        model.addAttribute("patients",patientsRepository.findAllById(Collections.singleton(patientId)));
//        Optional<Doctors> doctors = doctorsRepository.findById(doctorId);
        String title = "Deleting the below Patient from the database";
        model.addAttribute("title",title);
        return "patients/delete";
    }

    @PostMapping("delete")
    public String processDeletePatientsForm(@RequestParam(required = false) int[] patientIds) {
        if (patientIds != null) {
            for (int id : patientIds) {
                patientsRepository.deleteById(id);
            }
        }
        return "redirect:";
    }

    @GetMapping("edit/{patientId}")
    public String displayEditForm(Model model,@PathVariable int patientId,@ModelAttribute Patients patients) {
        //model.addAttribute("doctors",doctorsRepository.findAllById(Collections.singleton(doctorId)));
        //model.addAttribute("doctors",doctorsRepository.findById(doctorId));
        Optional<Patients> result = patientsRepository.findById(patientId);
        patients = result.get();
        String title = "Editing the below Patients details in the database";
        model.addAttribute("title",title);
        model.addAttribute("patients",patients);
        return "patients/edit";
    }

    @PostMapping("edit")
    public String processEditForm(Model model, @RequestParam(required = false) Integer patientId,@ModelAttribute Patients patients,Errors errors){
        if (errors.hasErrors()) {
            model.addAttribute("title", "Edit Patient Details");
            return "patients/edit";
        }
        Optional<Patients> result = patientsRepository.findById(patientId);
        Patients editPatient = result.get();
        editPatient.setPatientName(patients.getPatientName());
        editPatient.setAge(patients.getAge());
        editPatient.setGender(patients.getGender());
        //model.addAttribute("doctors",editDoctor);
        patientsRepository.save(editPatient);
        return "redirect:";
    }
}
