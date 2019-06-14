package generics;


import com.sun.tools.javac.main.Main;

public class Cifa {
    int a ;
    int c = a + 1;

    public static void main(String[] args) {
        Main compiler = new Main("javac");
        System.out.println(compiler.compile());
    }
}
