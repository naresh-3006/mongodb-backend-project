package com.student;

import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface StudentRepository
        extends MongoRepository<Student, String> {

    List<Student> findByName(String name);

    List<Student> findByBranch(String branch);

    List<Student> findByAgeGreaterThan(int age);
}