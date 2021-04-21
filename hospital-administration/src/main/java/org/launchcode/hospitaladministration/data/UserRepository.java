package org.launchcode.hospitaladministration.data;

import org.launchcode.hospitaladministration.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
    User findByUsername(String username);
}
