package dao;

import domain.Student;

import java.util.List;

public interface StudentDao {
    public Student getById(int id);

    public void add(Student s);

    List<Student> selectAll();
}
