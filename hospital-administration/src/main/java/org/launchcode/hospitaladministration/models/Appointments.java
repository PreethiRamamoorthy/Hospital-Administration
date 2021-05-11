package org.launchcode.hospitaladministration.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.time.LocalDate;

@Entity
@Table(name = "appointments")
public class Appointments extends AbstractEntity{

    //@DateTimeFormat(pattern="yyyy-MM-dd")
    //@DateTimeFormat(pattern = "MM/dd/yyyy")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSX")
    //@Future
    private String apptDate;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "patients_id")
    //@Size(min=1,message="Name cannot be empty")
    //private String patientName;
    private Patients patients;

    @ManyToOne(cascade = CascadeType.ALL)
    //@ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "doctors_id")
    //@Size(min=1,message="Name cannot be empty")
    //private String doctorName;
    private Doctors doctors;

    @NotBlank
    @Size(min=3,message="Complaint cannot be empty")
    private String complaint;

    public Appointments() {}

    public Appointments(String apptDate, Patients patients, Doctors doctors, String complaint) {
        this.apptDate = apptDate;
        this.patients = patients;
        this.doctors = doctors;
        this.complaint = complaint;
    }

    public String getApptDate() {
        return apptDate;
    }

    public void setapptDate(String apptDate) {
        this.apptDate = apptDate;
    }

    public Patients getPatients() {
        return patients;
    }

    public void setPatients(Patients patients) {
        this.patients = patients;
    }

    public Doctors getDoctors() {
        return doctors;
    }

    public void setDoctors(Doctors doctors) {
        this.doctors = doctors;
    }

    public String getComplaint() {
        return complaint;
    }

    public void setComplaint(String complaint) {
        this.complaint = complaint;
    }
}
