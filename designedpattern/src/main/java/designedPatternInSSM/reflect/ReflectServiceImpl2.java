package designedPatternInSSM.reflect;

import java.lang.reflect.InvocationTargetException;

public class ReflectServiceImpl2 {
    private String name;
    public ReflectServiceImpl2(String name){
        this.name = name;
    }
    public void sayHello(){
        System.err.println("hello " + name);
    }
    public static ReflectServiceImpl2 getInstance(){
        ReflectServiceImpl2 object = null;
        try{
            object = (ReflectServiceImpl2) Class.forName("designedPatternInSSM.reflect.ReflectServiceImpl2").getConstructor(String.class).newInstance("张三");
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return object;
    }
    static {
        System.err.println("hello");
    }
}
