package org.launchcode.hospitaladministration.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Patients extends AbstractEntity {

    @NotBlank
    @Size(min=1,message="Patient name cannot be empty")
    private String patientName;

    @NotNull
    private int age;

    @NotBlank
    private String gender;

    @OneToMany(mappedBy = "patients",cascade = {CascadeType.ALL})
    private final List<Appointments> appointments = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "doctors_id")
    private Doctors doctors;

    public Patients(){}

    public Patients(String patientName, int age, String gender, Doctors doctors) {
        this.patientName = patientName;
        this.age = age;
        this.gender = gender;
        this.doctors = doctors;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public List<Appointments> getAppointments() {
        return appointments;
    }

    public Doctors getDoctors() {
        return doctors;
    }

    public void setDoctors(Doctors doctors) {
        this.doctors = doctors;
    }
}
