package edu.mum.cs.cs425.eregistrar.repository;

import edu.mum.cs.cs425.eregistrar.model.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends CrudRepository<Student, Integer> {
    public abstract Iterable<Student> getAllByStudentNumberContainsOrderByFirstName(String s);
    public abstract Iterable<Student> getAllByStudentNumberContainsOrFirstNameContainsOrMiddleNameContainsOrLastNameContainsOrderByFirstName(String s1,String s2, String s3, String s4);
}
