package org.launchcode.hospitaladministration.data;

import org.launchcode.hospitaladministration.models.Patients;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface PatientsRepository extends CrudRepository<Patients,Integer> {
}
