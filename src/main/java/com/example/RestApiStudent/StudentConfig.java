package com.example.RestApiStudent;


import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StudentConfig {
	
	@Bean
	CommandLineRunner commandLineRunner(StudentRepository repository) {
		return args -> {
			Student amera = new Student(
					"Amera",
					"K",
					"amera.k@hotmail.com",
					26
			);
			Student jalal = new Student(
					"Jalal",
					"K",
					"jalal.k@gmail.com",
					24
			);
			
			repository.saveAll(List.of(amera,jalal));
					
		};
	}

}
