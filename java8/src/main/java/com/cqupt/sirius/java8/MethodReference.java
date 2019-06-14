package com.cqupt.sirius.java8;



import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class MethodReference {
    public static void main(String[] args) {
//        Consumer<String> consumer = s -> System.out.println(s);
//        useConsumer(consumer, "hello Alex");
        useConsumer(s -> System.out.println(s), "hello Alex");
        System.out.println("===============");
        useConsumer(System.out::println, "hello Alex");

        List<Apple> list = Arrays.asList(
                new Apple("abc",123),
                new Apple("Green",110),
                new Apple("red",123));
        System.out.println(list);
        list.sort((a1, a2)->a1.getColor().compareTo(a2.getColor()));
        System.out.println(list);
        list.stream().forEach(a -> System.out.println(a));
        System.out.println("===============");
        list.stream().forEach(System.out::println);

        int value = Integer.parseInt("123");
        Function<String, Integer> f = Integer::parseInt;
        System.out.println(f.apply("123"));

        BiFunction<String, Integer, Character> f2 = String::charAt;
        System.out.println(f2.apply("hello",2));

        String string = new String("Hello");
        Function<Integer, Character> f3 = string::charAt;

        BiFunction<String, Long, Apple> appleBiFunction1 = new BiFunction<String, Long, Apple>() {
            @Override
            public Apple apply(String s, Long aLong) {
                return new Apple(s,aLong);
            }
        };
        BiFunction<String, Long, Apple> appleBiFunction2 = (s, l)-> new Apple(s, l);
        BiFunction<String, Long, Apple> appleBiFunction3 = Apple::new;




    }

    private static <T> void useConsumer(Consumer<T> consumer, T t){
        consumer.accept(t);
        consumer.accept(t);
    }
}
