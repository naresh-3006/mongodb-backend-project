package com.student;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentRepository repository;

    public StudentController(StudentRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public Student addStudent(@RequestBody Student student) {
        return repository.save(student);
    }

    @GetMapping
    public List<Student> getAllStudents() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Student getStudent(@PathVariable String id) {
        return repository.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public Student updateStudent(
            @PathVariable String id,
            @RequestBody Student updatedStudent) {

        return repository.findById(id).map(student -> {
            student.setName(updatedStudent.getName());
            student.setEmail(updatedStudent.getEmail());
            student.setBranch(updatedStudent.getBranch());
            student.setAge(updatedStudent.getAge());

            return repository.save(student);
        }).orElse(null);
    }

    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable String id) {

        if (!repository.existsById(id)) {
            return "Student not found";
        }

        repository.deleteById(id);

        return "Student deleted successfully";
    }

    @GetMapping("/search/name/{name}")
    public List<Student> searchByName(@PathVariable String name) {
        return repository.findByName(name);
    }

    @GetMapping("/search/branch/{branch}")
    public List<Student> searchByBranch(@PathVariable String branch) {
        return repository.findByBranch(branch);
    }
}