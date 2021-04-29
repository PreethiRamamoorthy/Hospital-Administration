package org.launchcode.hospitaladministration.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
public class Appointments extends AbstractEntity{

    @NotNull(message="Date field cannot blank")
    //@DateTimeFormat(pattern="yyyy-MM-dd")
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    @Future
    //@DateTimeFormat
    private Date apptDate;

    @ManyToOne
    @NotNull(message="Name cannot be empty")
    //@Size(min=1,message="Name cannot be empty")
    //private String patientName;
    private Patients patients;


    @NotBlank
    @Size(min=1,message="Name cannot be empty")
    private String doctorName;

    @NotBlank
    @Size(min=3,message="Complaint cannot be empty")
    private String complaint;

    public Appointments() {}

    public Appointments(Date apptDate, Patients patients, String doctorName, String complaint) {
        this.apptDate = apptDate;
        this.patients = patients;
        this.doctorName = doctorName;
        this.complaint = complaint;
    }

    public Date getApptDate() {
        return apptDate;
    }

    public void setapptDate(Date apptDate) {
        this.apptDate = apptDate;
    }

    public Patients getPatients() {
        return patients;
    }

    public void setPatients(Patients patients) {
        this.patients = patients;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getComplaint() {
        return complaint;
    }

    public void setComplaint(String complaint) {
        this.complaint = complaint;
    }
}
