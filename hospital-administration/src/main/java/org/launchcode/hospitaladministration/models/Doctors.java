package org.launchcode.hospitaladministration.models;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Doctors extends AbstractEntity{

    @NotBlank
    @Size(min=1,message="Name cannot be empty")
    private String doctorName;

    @NotBlank
    @Size(min=5,max=30,message="Please enter a valid Doctor Speciality.")
    private String doctorSpeciality;

    @OneToMany(mappedBy = "doctors")
    private final List<Appointments> appointments = new ArrayList<>();

    @OneToMany(mappedBy = "doctors")
    private final List<Patients> patients = new ArrayList<>();

    public Doctors(){}

    public Doctors(String doctorName, String doctorSpeciality) {
        this.doctorName = doctorName;
        this.doctorSpeciality = doctorSpeciality;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getDoctorSpeciality() {
        return doctorSpeciality;
    }

    public void setDoctorSpeciality(String doctorSpeciality) {
        this.doctorSpeciality = doctorSpeciality;
    }

    public List<Appointments> getAppointments() {
        return appointments;
    }
}
