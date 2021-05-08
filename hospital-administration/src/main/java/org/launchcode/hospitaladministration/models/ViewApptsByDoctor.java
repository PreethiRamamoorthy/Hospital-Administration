package org.launchcode.hospitaladministration.models;

import org.springframework.data.repository.CrudRepository;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;


public interface ViewApptsByDoctor {

    //int getId();
    //@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSX")
    String getapptDate();

    String getDoctorName();

    String getComplaint();

}
