package org.launchcode.hospitaladministration.data;

import org.launchcode.hospitaladministration.models.Doctors;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
@Transactional
public interface DoctorsRepository extends CrudRepository<Doctors,Integer> {

}
