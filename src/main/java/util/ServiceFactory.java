package util;

public class ServiceFactory {
    public static Object getService(Object obj){
        return new TransactionInvocationHandler(obj).getProxy();
    }
}
