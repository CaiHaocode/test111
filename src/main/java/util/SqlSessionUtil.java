package util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class SqlSessionUtil {
    private static SqlSessionFactory sqlSessionFactory;
    //不允许外面使用new方法调用
    private SqlSessionUtil(){};
    //获取连接
    static {
        String resource = "mybatis-config.xml";
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }
    /**
     * 存储SQLSession对象容器池
     * 原理：所有事务使用一个SqlSession,
     *      在第一次使用时 容器是没有数据的，其实是一个创建并存储的过程
     *      在之后的使用，其实使用的是第一次创建的session
     *
     */
    private static ThreadLocal<SqlSession> t= new ThreadLocal<SqlSession>();
    //获取SQLsession对象
    public static SqlSession getSession(){
        SqlSession sqlSession = t.get();
        if(sqlSession == null){
            //如果ThreadLocal无sqlsesion 则创建对象,并加入容器池
            sqlSession = sqlSessionFactory.openSession();
            t.set(sqlSession);
        }
        return sqlSession;
    }
    //关闭SqlSession对象
    public static void myClose(SqlSession sqlSession){
        if(sqlSession!= null){
            sqlSession.close();

            t.remove();
        }
    }
}
