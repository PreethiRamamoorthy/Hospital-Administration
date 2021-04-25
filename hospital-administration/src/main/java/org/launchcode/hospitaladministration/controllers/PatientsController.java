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

    @GetMapping("/goback")
    public String gobackToIndexPage(){
        return "/templates/index";
    }
}
