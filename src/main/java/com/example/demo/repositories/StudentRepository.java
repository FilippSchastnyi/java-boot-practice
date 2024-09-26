package com.example.demo.repositories;

import com.example.demo.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

	Optional<Student> findStudentByEmail(String email);
	Optional<Student> findStudentById(Long id);
}
/*
StudentRepository наследует JpaRepository, что означает, что он будет иметь доступ ко всем
стандартным методам CRUD (создание, чтение, обновление, удаление) для сущности Student.
Параметры <Student, Long> указывают на то, что Student — это тип сущности,
а Long — тип идентификатора этой сущности.

Это метод, который будет искать студента по его электронной почте.
 Spring Data JPA автоматически сгенерирует реализацию этого метода на основе имени.
*/
