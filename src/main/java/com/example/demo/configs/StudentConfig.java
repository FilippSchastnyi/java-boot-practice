package com.example.demo.configs;


import com.example.demo.models.Student;
import com.example.demo.repositories.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {

	@Bean
	CommandLineRunner commandLineRunner(StudentRepository studentRepository) {
		return args -> {
			Student doe = new Student(
					1L,
					"Doe",
					"john.doe@example.com",
					LocalDate.of(2000, Month.JANUARY, 5)
			);

			Student alex = new Student(
					2L,
					"Alex",
					"alex.doe@example.com",
					LocalDate.of(2004, Month.JANUARY, 5)
			);

			studentRepository.saveAll(
					List.of(doe, alex)
			);
		};
	}
}
