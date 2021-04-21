package org.launchcode.hospitaladministration.models;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class Doctors extends AbstractEntity{

    @NotBlank
    @Size(min=5,message="Name cannot be empty")
    private String doctorName;

    @NotBlank
    @Size(min=5,max=30,message="Please enter a valid Doctor Speciality.")
    private String doctorSpeciality;

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
}
