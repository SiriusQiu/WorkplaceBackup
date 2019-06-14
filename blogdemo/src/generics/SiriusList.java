package generics;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * 1. 在末端添加元素
 * 2. 给定序号添加元素，其余元素后移
 * 3. 删除给定序号元素，其余元素前移
 * 4. 清空该list
 * 5. 返回大小
 * 6. 返回迭代器
 * 7. 扩容
 * 8. 自动扩容
 * 在初始化的时候需要根据默认大小来初始化数组
 * @param <T>
 */
public class SiriusList<T> implements Iterable<T> {
    private static final int DEFAULT_CAPACITY = 10;//数组初始化时的默认大小
    private int size;//当前容器中元素的数量
    private T[] items;//实际存放元素的数组
    @SuppressWarnings("unchecked")
    public SiriusList(){
        size = 0;
        items = (T[]) new Object[DEFAULT_CAPACITY];
    }

    public int size(){
        return size;
    }

    public static void main(String[] args) {
        SiriusList<Integer> list = new SiriusList<>();
        System.out.println(list.size);
    }


    @Override
    public Iterator<T> iterator() {
        return null;
    }
}
