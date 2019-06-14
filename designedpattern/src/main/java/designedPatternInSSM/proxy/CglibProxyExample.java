package designedPatternInSSM.proxy;

import designedPatternInSSM.reflect.ReflectServiceImpl;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CglibProxyExample implements MethodInterceptor {
    public Object getProxy(Class cls){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(cls);
        enhancer.setCallback(this);
        return enhancer.create();
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        System.out.println("调用真实对象前");
        Object result = proxy.invokeSuper(obj, args);
        System.err.println("调用真实对象后");
        return result;
    }

    public static void testCGLIBProxy(){
        CglibProxyExample cpe = new CglibProxyExample();
        ReflectServiceImpl obj = (ReflectServiceImpl)cpe.getProxy(ReflectServiceImpl.class);
        obj.sayHello("张三");
    }
}
