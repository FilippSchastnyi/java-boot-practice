package com.example.demo.services;

import com.example.demo.models.Student;
import com.example.demo.repositories.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
public class StudentService {

	private final StudentRepository studentRepository;

	@Autowired
	public StudentService(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}

	public List<Student> getStudents() {
		return studentRepository.findAll();
	}

	public void addNewStudent(Student student) {
		Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
		if (studentOptional.isPresent()) {
			throw new IllegalStateException("Student with email exists");
		}
		studentRepository.save(student);
	}

	public void deleteStudent(Long studentId) {
		boolean isStudentExist = studentRepository.existsById(studentId);
		if (!isStudentExist) {
			throw new IllegalStateException("Student with this id does not exist");
		}

		studentRepository.deleteById(studentId);
	}

	@Transactional
	public void updateStudentNameAndEmail(Long studentId, String name, String email) {
		Student student = studentRepository.findStudentById(studentId).orElseThrow(() -> new IllegalStateException("Did not " +
				"find student with"));

		if (name != null && !name.isEmpty() && !Objects.equals(student.getName(), name)) {
			student.setName(name);
		}

		if (email != null && !email.isEmpty() && !Objects.equals(email, student.getEmail())) {
			Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);
			if (studentOptional.isPresent()){
				throw new IllegalStateException("Student with email exists");
			}
			student.setEmail(email);
		}
	}
}
