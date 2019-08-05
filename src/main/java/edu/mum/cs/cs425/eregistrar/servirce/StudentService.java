package edu.mum.cs.cs425.eregistrar.servirce;

import edu.mum.cs.cs425.eregistrar.model.Student;
import org.springframework.data.domain.Page;

public interface StudentService {
    public abstract Page<Student> getAllStudents(int a);
    public abstract Student saveStudent(Student s);
    public abstract Student getStudentById(Integer studentId);
    public  abstract void deleteStudentByID(Integer id);
    public abstract Iterable<Student> getAllStudentsSorted(String s);
    public abstract Iterable<Student> searchStudent(String s);
}
