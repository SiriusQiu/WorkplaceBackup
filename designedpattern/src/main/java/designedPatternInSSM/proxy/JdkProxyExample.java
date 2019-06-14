package designedPatternInSSM.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JdkProxyExample implements InvocationHandler {
    private Object target = null;
    public Object bind(Object object){
        this.target = object;
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),target.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("进入代理逻辑方法");
        System.out.println("在调度真实对象之前的服务");
        Object obj = method.invoke(target, args);
        System.out.println("在调度真实对象之后的服务");
        return obj;
    }
    public static void testJdkProxy(){
        JdkProxyExample jdk = new JdkProxyExample();
        HelloWorld proxy = (HelloWorld) jdk.bind(new HelloWorldImpl());
        proxy.sayHelloWorld();
    }
}
