package generics;//: generics/ArrayMaker.java
import java.lang.reflect.*;
import java.util.*;

public class ArrayMaker<T> {
  private Class<T> kind;//存放类型信息
  public ArrayMaker(Class<T> kind) { this.kind = kind; }
  T[] create(int size) {
    return (T[])Array.newInstance(kind, size);
  }//使用Array.newInstance创建数组
  public static void main(String[] args) {
    ArrayMaker<String> stringMaker =
      new ArrayMaker<String>(String.class);
    String[] stringArray = stringMaker.create(9);
    System.out.println(Arrays.toString(stringArray));
  }
} /* Output:
[null, null, null, null, null, null, null, null, null]
*///:~
