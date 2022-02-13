package test;

import domain.Student;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import service.StudentService;
import service.impl.StudentServiceImpl;
import util.ServiceFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Author 北京动力节点
 */
public class Test1 {

    public static void main(String[] args) {
        /*调用实际的实现类接口，没有提交事务
        StudentService ss = new StudentServiceImpl();*/
        //使用动态代理 提交事务
        StudentService ss = (StudentService) ServiceFactory.getService(new StudentServiceImpl());
        List<Student> list = ss.selectAll();
        for (Student s:list) {
            System.out.println(s);
        }
//        Student s = new Student();
//        s.setId(5);
//        s.setName("nnn");
//        ss.add(s);
    }

}






























