package service.impl;

import dao.StudentDao;
import domain.Student;
import service.StudentService;
import util.SqlSessionUtil;

import java.util.List;

public class StudentServiceImpl implements StudentService {


    //业务层调用dao层接口
    private StudentDao studentDao =  SqlSessionUtil.getSession().getMapper(StudentDao.class);
    @Override
    public Student getById(int id) {
        Student s = studentDao.getById(id);
        return s;
    }
    @Override
    public List<Student> selectAll() {
        List<Student> list = studentDao.selectAll();
        return list;
    }
    @Override
    public void add(Student s) {
        studentDao.add(s);
    }
}
