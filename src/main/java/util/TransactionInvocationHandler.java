package util;

import org.apache.ibatis.session.SqlSession;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class TransactionInvocationHandler implements InvocationHandler {
    //真实的实现对象
    private Object target;
    public TransactionInvocationHandler(Object target){
        this.target = target;
    }
    //代理执行的业务
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object obj = null;
        SqlSession  sqlSession = null;
        try {
            //获取连接对象
            sqlSession = SqlSessionUtil.getSession();
            //实际上要执行的业务
            obj = method.invoke(target,args);
            //业务执行完毕后，提交事务
            sqlSession.commit();
        }catch (Exception e){
            //业务执行失败 回滚
            sqlSession.rollback();
            e.printStackTrace();
        }finally {
            SqlSessionUtil.myClose(sqlSession);
        }
        return obj;
    }
    //获取代理对象
    public Object getProxy(){
        return  Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(), this);
    }
}
