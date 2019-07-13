package compile;
import com.sun.tools.javac.main.Main;


public class Test {
    public static void main(String[] args) {
         Main compiler = new Main("javac");
         try{
             Main.Result result = compiler.compile(new String[] {"C:\\Users\\Sirius\\项目目录\\Java学习\\thinkinginjava\\src\\compile\\Cifa.java"});
             System.err.println(result);
         }catch (Exception e){
             e.printStackTrace();
         }
    }
}
