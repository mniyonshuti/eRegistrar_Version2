package edu.mum.cs.cs425.eregistrar.servirce.implementation;

import edu.mum.cs.cs425.eregistrar.model.Student;
import edu.mum.cs.cs425.eregistrar.repository.StudentRepository;
import edu.mum.cs.cs425.eregistrar.servirce.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImplementation implements StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Override
    public Page<Student> getAllStudents(int pageNo) {
        return (Page<Student>) studentRepository.findAll(PageRequest.of(pageNo, 4, Sort.by("firstName")));
    }

    @Override
    public Student saveStudent(Student s) {
        return studentRepository.save(s);
    }

    @Override
    public Student getStudentById(Integer studentId) {
        return studentRepository.findById(studentId).orElse(null);
    }

    @Override
    public void deleteStudentByID(Integer id) {
            studentRepository.deleteById(id);
    }

    @Override
    public Iterable<Student> getAllStudentsSorted(String s) {
        return studentRepository.getAllByStudentNumberContainsOrderByFirstName("");
    }

    @Override
    public Iterable<Student> searchStudent(String s) {
        return studentRepository.getAllByStudentNumberContainsOrFirstNameContainsOrMiddleNameContainsOrLastNameContainsOrderByFirstName
                (s,s,s,s);
    }

}
