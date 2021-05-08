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

import javax.validation.Path;
import javax.validation.Valid;
import java.util.Collections;
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

    @RequestMapping("view-all-appts")
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
            model.addAttribute("patients",patientsRepository.findAll());
            model.addAttribute("doctors",doctorsRepository.findAll());
            return "appointments/create";
        }
        appointmentsRepository.save(appointments);
        return "redirect:";
    }

    @RequestMapping("edit")
    public String displayEditForm(Model model,@ModelAttribute Appointments appointments) {
        model.addAttribute("title","All Appointments");
        model.addAttribute("appointments",appointmentsRepository.findAll());
        return "appointments/edit";
    }

    @GetMapping("edit/{appointmentId}")
    public String renderApptIDInEditForm(Model model,@PathVariable int appointmentId,@ModelAttribute Appointments appointments) {
        Optional<Appointments> result = appointmentsRepository.findById(appointmentId);
        appointments = result.get();
        String title = "Editing the below Appointment details in the database";
        model.addAttribute("title",title);
        model.addAttribute("appointments",appointments);
        return "appointments/process-edit";
    }

    @PostMapping("process-edit")
    public String processEditForm(Model model, @RequestParam(required = false) Integer appointmentId,@ModelAttribute Appointments appointments,Errors errors){
        if (errors.hasErrors()) {
            model.addAttribute("title", "Edit Appointment Details");
            return "appointments/process-edit";
        }
        Optional<Appointments> result = appointmentsRepository.findById(appointmentId);
        Appointments editAppts = result.get();
        editAppts.setapptDate(appointments.getApptDate());
        editAppts.setComplaint(appointments.getComplaint());
        appointmentsRepository.save(editAppts);
        return "redirect:";
    }

    @RequestMapping("cancel")
    public String displayCancelApptForm(Model model,@ModelAttribute Appointments appointments) {
        model.addAttribute("title","All Appointments");
        model.addAttribute("appointments",appointmentsRepository.findAll());
        return "appointments/cancel";
    }

    @GetMapping("cancel/{appointmentId}")
    public String renderCancelApptForm(Model model,@PathVariable int appointmentId) {
        model.addAttribute("appointments",appointmentsRepository.findAllById(Collections.singleton(appointmentId)));
        String title = "Deleting the below Doctor from the database";
        model.addAttribute("title",title);
        return "appointments/process-cancel";
    }

    @PostMapping("process-cancel")
    public String processDeleteDoctorsForm(@RequestParam(required = false) int[] appointmentIds) {
        if (appointmentIds != null) {
            for (int id : appointmentIds) {
                appointmentsRepository.deleteById(id);
            }
        }
        return "redirect:";
    }

    @RequestMapping("view-appts-by-doctor")
    public String viewApptsByDoctorForm(Model model, Appointments appointments){
        model.addAttribute("appointments",appointmentsRepository.findAll());
        return "appointments/view-appts-by-doctor";
    }

    @GetMapping("view-appts-by-doctor/{doctorId}")
    public String renderViewApptsByDoctorForm(Model model, @PathVariable int doctorId,Appointments appointments){
//        Optional<Appointments> result = appointmentsRepository.findById(doctorId);
//        appointments = result.get();
        //model.addAttribute("appointments",appointmentsRepository.findAllById(Collections.singleton(doctorId)));
        model.addAttribute("appointments",appointmentsRepository.findByDoctorId(doctorId));
        System.out.println("$$$$"+ appointments.toString());
        //model.addAttribute("appointments",appointments);
        return "appointments/process-view-appts-by-doctor";
    }

//    @PostMapping("process-view-appts-by-doctor")
//    public String processViewApptsByDoctor(Model model,@RequestParam(required = false) Integer doctorId,@ModelAttribute Appointments appointments,@ModelAttribute Doctors doctors, Errors errors){
//        if (errors.hasErrors()) {
//            model.addAttribute("title", "View All Appointment by Doctor");
//            return "appointments/process-view-appts-by-doctor";
//        }
//        Optional<Appointments> result = appointmentsRepository.findById(doctorId);
//        appointments = result.get();
//        //Doctors matchDoctorName = (Doctors) doctorsRepository.findByName(doctors.getDoctorName());
//        if(appointments.getDoctors().getId() == doctors.getId()) {
//            if ((appointments.getDoctors().getDoctorName()).equals(doctorsRepository.findByName(doctors.getDoctorName()))) {
//                model.addAttribute("appointments", appointments);
//            }
//        }
//        return "redirect:";
//    }

//    @PostMapping("process-view-appts-by-doctor")
//    public String processViewApptsByDoctor(Model model, @RequestParam(required = false) Integer doctorId, @ModelAttribute Doctors doctors, @ModelAttribute Appointments appointments, Errors errors){
//        Optional<Appointments> result = appointmentsRepository.findById(doctorId);
//        appointments = result.get();
//        if(appointments.getDoctors().getId() == doctors.getId()) {
//            if(appointments.getDoctors().getId() == doctorId) {
//                model.addAttribute("appointments", appointments);
//            }
//        }
//        return "redirect:";
//    }
}
