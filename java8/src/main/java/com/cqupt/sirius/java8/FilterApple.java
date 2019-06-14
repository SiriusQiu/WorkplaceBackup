package com.cqupt.sirius.java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FilterApple {

    @FunctionalInterface
    public static interface AppleFilter{
        boolean filter(Apple apple);
    }

    public static List<Apple> findApple(List<Apple> apples, AppleFilter appleFilter){
        List<Apple> list = new ArrayList<>();
        for (Apple apple : apples){
            if (appleFilter.filter(apple)){
                list.add(apple);
            }
        }
        return list;
    }

    public static class GreenAnd160WeightFilter implements AppleFilter{
        @Override
        public boolean filter(Apple apple) {
            return (apple.getColor().equals("green")&&apple.getWeight()>=150);
        }
    }

    public static class YellowAnd150WeightFilter implements AppleFilter{
        @Override
        public boolean filter(Apple apple) {
            return (apple.getColor().equals("yellow")&&apple.getWeight()<150);
        }
    }


    public static List<Apple> findGreenApple(List<Apple> apples){
        List<Apple> list = new ArrayList<>();
        for (Apple apple : apples){
            if ("green".equals(apple.getColor())){
                list.add(apple);
            }
        }
        return list;
    }

    public static List<Apple> findApple(List<Apple> apples, String color){
        List<Apple> list = new ArrayList<>();
        for (Apple apple : apples){
            if (color.equals(apple.getColor())){
                list.add(apple);
            }
        }
        return list;
    }

    public static void main(String[] args) {
        List<Apple> list = Arrays.asList(new Apple("green",160),
                new Apple("yellow",120),
                new Apple("green",170));
//        List<Apple> greenApples = findGreenApple(list);
//        assert greenApples.size()==2;
//        List<Apple> redApples = findApple(list, "red");
//        assert redApples.size()==2;

//        List<Apple> result = findApple(list, new GreenAnd160WeightFilter());
//        System.out.println(result);
//
//        List<Apple> yellowList = findApple(list, new AppleFilter() {
//            @Override
//            public boolean filter(Apple apple) {
//                return "yellow".equals(apple.getColor());
//            }
//        });
//        System.out.println(yellowList);

        List<Apple> lambdaResult = findApple(list, (Apple apple) -> {
            return apple.getColor().equals("green");
        });
        System.out.println(lambdaResult);
    }
}
