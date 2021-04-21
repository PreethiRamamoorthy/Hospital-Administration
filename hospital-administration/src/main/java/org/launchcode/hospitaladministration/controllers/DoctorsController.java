package org.launchcode.hospitaladministration.controllers;

import org.launchcode.hospitaladministration.data.DoctorsRepository;
import org.launchcode.hospitaladministration.models.Doctors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

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
}
