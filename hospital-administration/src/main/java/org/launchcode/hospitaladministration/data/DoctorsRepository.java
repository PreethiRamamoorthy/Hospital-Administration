package org.launchcode.hospitaladministration.data;

import org.launchcode.hospitaladministration.models.Doctors;
import org.springframework.data.repository.CrudRepository;

public interface DoctorsRepository extends CrudRepository<Doctors,Integer> {
}
