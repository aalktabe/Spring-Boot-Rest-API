package com.example.RestApiStudent;


import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
	
	StudentRepository studentRepository;
	
	@Autowired
	public StudentService(StudentRepository studentRepository) {
		super();
		this.studentRepository = studentRepository;
	}

	public List<Student> getStudents() {		
		return studentRepository.findAll();
	}
	
	public Optional<Student> getStudentByID(Long id) {
		boolean exists = studentRepository.existsById(id);
		if(!exists) {
			throw new IllegalStateException("student with id " + id + " does not exists");
		}
		
		return studentRepository.findById(id);
	}
	
	public void addNewStudent(Student student) {
		Optional<Student> studentByEmail =  studentRepository.findStudentByEmail(student.getEmail());
		if(studentByEmail.isPresent()) {
			throw new IllegalStateException("email taken");
		}
		
		studentRepository.save(student);
	}

	public void deleteStudent(Long id) {
	
		boolean exists = studentRepository.existsById(id);
		if(!exists) {
			throw new IllegalStateException("student with id " + id + " does not exists");
		}
		
		studentRepository.deleteById(id);
		
	}
	
	@Transactional
	public void updateStudent(Long id, String firstName, String lastName, String email, Integer age) {
		Student student = studentRepository.findById(id)
				.orElseThrow(() -> new IllegalStateException(
						"student with id " + id + " does not exists"));
		
		if(firstName != null && firstName.length() > 0 && !Objects.equals(student.getFirstName(), firstName)) {
			student.setFirstName(firstName);
		}
		
		if(lastName != null && lastName.length() > 0 && !Objects.equals(student.getLastName(), lastName)) {
			student.setLastName(lastName);
		}
		
		if(email != null && email.length() > 0 && !Objects.equals(student.getEmail(), email)) {
			Optional<Student> StudentEmail =  studentRepository.findStudentByEmail(email);
			if(StudentEmail.isPresent()) {
				throw new IllegalStateException("email taken");
			}
			
			student.setEmail(email);
		}
		
		if(age != null && age > 0 && student.getAge() != age) {
			student.setAge(age);
		}
		
	}

	
}
