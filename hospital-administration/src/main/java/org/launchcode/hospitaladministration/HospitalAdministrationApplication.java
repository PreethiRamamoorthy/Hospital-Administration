package org.launchcode.hospitaladministration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
//@EnableJpaAuditing
public class HospitalAdministrationApplication {

	public static void main(String[] args) {
		SpringApplication.run(HospitalAdministrationApplication.class, args);
	}

}
