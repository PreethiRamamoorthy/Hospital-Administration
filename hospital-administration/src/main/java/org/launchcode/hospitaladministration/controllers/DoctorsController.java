package org.launchcode.hospitaladministration.controllers;

import org.launchcode.hospitaladministration.data.DoctorsRepository;
import org.launchcode.hospitaladministration.models.Doctors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.Optional;

@Controller
@RequestMapping("doctors")
public class DoctorsController {

    @Autowired
    private DoctorsRepository doctorsRepository;

    @GetMapping()
    public String displayAllDoctors(Model model){
        model.addAttribute("title","All Doctors");
        model.addAttribute("doctors",doctorsRepository.findAll());
        return "doctors/index";
    }

    @GetMapping("create")
    public String renderCreateDoctorsForm(Model model){
        model.addAttribute("title","Add a Doctor");
        model.addAttribute(new Doctors());
        return "doctors/create";
    }

    @PostMapping("create")
    public String processCreateDoctorsForm(@ModelAttribute @Valid Doctors doctors, Errors errors, Model model){
        if (errors.hasErrors()) {
            model.addAttribute("title", "Add a Doctor");
            model.addAttribute(new Doctors());
            return "doctors/create";
        }
        doctorsRepository.save(doctors);
        return "redirect:";
    }

    @GetMapping("edit/{doctorId}")
    public String displayEditForm(Model model,@PathVariable int doctorId) {
        //model.addAttribute("doctors",doctorsRepository.findAllById(Collections.singleton(doctorId)));
        model.addAttribute("doctors",doctorsRepository.findById(doctorId));
//        Optional<Doctors> doctors = doctorsRepository.findById(doctorId);
        String title = "Editing the below Doctor details in the database";
        model.addAttribute("title",title);
        return "doctors/edit";
    }

    @PostMapping("edit")
    public String processEditForm(Model model, @RequestParam(required = false) Integer doctorId, String doctorName, String doctorSpeciality){
//        if (errors.hasErrors()) {
//            model.addAttribute("title", "Edit Doctor Details");
//            return "doctors/edit";
//        }
        Optional<Doctors> doctors = doctorsRepository.findById(doctorId);
        Doctors editDoctor = doctors.get();
        editDoctor.setDoctorName(doctorName);
        editDoctor.setDoctorSpeciality(doctorSpeciality);
        model.addAttribute("doctors",editDoctor);
        doctorsRepository.save(editDoctor);
        return "redirect:";
   }

    @GetMapping("delete/{doctorId}")
    public String displayDeleteForm(Model model,@PathVariable int doctorId) {
        model.addAttribute("doctors",doctorsRepository.findAllById(Collections.singleton(doctorId)));
//        Optional<Doctors> doctors = doctorsRepository.findById(doctorId);
        String title = "Deleting the below Doctor from the database";
        model.addAttribute("title",title);
        return "doctors/delete";
    }

    @PostMapping("delete")
    public String processDeleteDoctorsForm(@RequestParam(required = false) int[] doctorIds) {
        if (doctorIds != null) {
            for (int id : doctorIds) {
                doctorsRepository.deleteById(id);
            }
        }
        return "redirect:";
    }
}
