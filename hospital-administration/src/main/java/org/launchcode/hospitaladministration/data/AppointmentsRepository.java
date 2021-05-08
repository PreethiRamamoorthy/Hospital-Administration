package org.launchcode.hospitaladministration.data;
import org.launchcode.hospitaladministration.models.Appointments;
import org.launchcode.hospitaladministration.models.Doctors;
import org.launchcode.hospitaladministration.models.Patients;
import org.launchcode.hospitaladministration.models.ViewApptsByDoctor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface AppointmentsRepository extends CrudRepository<Appointments,Integer> {
    @Query(value="SELECT a.appt_date,a.complaint,d.doctor_name FROM Appointments a JOIN Doctors d ON a.doctors_id = d.id WHERE a.doctors_id = :doctorId",nativeQuery = true)
    //@Query(value="SELECT * FROM Appointments a JOIN Doctors d ON a.doctors_id = d.id WHERE d.id = :doctorId",nativeQuery = true)
      //List<Doctors> findByDoctorId(@Param("doctorId")int doctorId);
      List<ViewApptsByDoctor> findByDoctorId(@Param("doctorId")int doctorId);

}
