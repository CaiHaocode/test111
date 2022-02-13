package service;

import domain.Student;

import java.util.List;

public interface StudentService {
    public Student getById(int id);
    public void add(Student s);

    List<Student> selectAll();
}
