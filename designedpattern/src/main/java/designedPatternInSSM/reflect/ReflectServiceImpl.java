package designedPatternInSSM.reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectServiceImpl {
    public void sayHello(String name){
        System.err.println("Hello " + name);
    }
    public static ReflectServiceImpl getInstance(){
        ReflectServiceImpl object = null;
        try{
            object = (ReflectServiceImpl) Class.forName("designedPatternInSSM.reflect.ReflectServiceImpl").newInstance();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return object;
    }
    static {
        System.err.println("hello");
    }
    public Object reflectMethod(){
        Object returnObj = null;
        ReflectServiceImpl target = new ReflectServiceImpl();
        try{
            Method method = ReflectServiceImpl.class.getMethod("sayHello", String.class);
            returnObj = method.invoke(target, "张三");
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return returnObj;
    }
    public Object reflect(){
        ReflectServiceImpl object = null;
        try{
            object = (ReflectServiceImpl) Class.forName("designedPatternInSSM.reflect.ReflectServiceImpl").newInstance();
            Method method = object.getClass().getMethod("sayHello", String.class);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return object;
    }
}
