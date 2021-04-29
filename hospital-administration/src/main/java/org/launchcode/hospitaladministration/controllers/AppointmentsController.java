package org.launchcode.hospitaladministration.controllers;

import org.launchcode.hospitaladministration.data.AppointmentsRepository;
import org.launchcode.hospitaladministration.data.DoctorsRepository;
import org.launchcode.hospitaladministration.data.PatientsRepository;
import org.launchcode.hospitaladministration.models.Appointments;
import org.launchcode.hospitaladministration.models.Doctors;
import org.launchcode.hospitaladministration.models.Patients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("appointments")
public class AppointmentsController {

    @Autowired
    private AppointmentsRepository appointmentsRepository;

    @Autowired
    private DoctorsRepository doctorsRepository;

    @Autowired
    private PatientsRepository patientsRepository;

    @GetMapping()
    public String index() {
        return "appointments/index";
    }

    @GetMapping("view-all-appts")
    public String displayAllAppointments(Model model){
        model.addAttribute("title","All Appointments");
        model.addAttribute("appointments",appointmentsRepository.findAll());
        return "appointments/view-all-appts";
    }

    @GetMapping("create")
    public String renderCreateAppointmentsForm(Model model){
        model.addAttribute("title","Schedule an Appointment");
        model.addAttribute(new Appointments());
        model.addAttribute("patients",patientsRepository.findAll());
        model.addAttribute("doctors",doctorsRepository.findAll());
        return "appointments/create";
    }

    @PostMapping("create")
    public String processCreateAppointmentsForm(@ModelAttribute @Valid Appointments appointments,Errors errors, Model model){
        if (errors.hasErrors()) {
            model.addAttribute("title", "Schedule an Appointment");
            model.addAttribute(new Appointments());
            model.addAttribute("patients",patientsRepository.findAll());
            model.addAttribute("doctors",doctorsRepository.findAll());
            return "appointments/create";
        }
        appointmentsRepository.save(appointments);
        return "redirect:";
    }

}
