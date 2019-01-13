import java.util.*;
import java.lang.*;
import java.io.*;

public class DynamicPDA{
     public static void main(String[] args){
        Scanner m = new Scanner(System.in);
        String state;
        Graph automata = new Graph();
        try {
          Scanner sc = new Scanner(new File("test.txt"));
          List<String> lines = new ArrayList<String>();
          while (sc.hasNextLine()) {
            lines.add(sc.nextLine());
          }
          String[] array = lines.toArray(new String[0]);
          int max = Integer.valueOf(array[0]);
          state=array[1];
          int at=0;
          int in=1;
          System.out.println("State :");
          for(int i=0;i<max;i++){
            String v = state.substring(at, in+1);
            System.out.println("=>"+v);
            automata.insertState(v);
            at=at+3;
            in=in+3;
          }
          max = Integer.valueOf(array[2]);
          int line=3;
          String transition;
          String old, next, terminal,top,pushS;
          at=0;
          in=1;
          for(int i=0;i<max;i++){
            transition=array[line];
            old=transition.substring(at,in+1);
            terminal=String.valueOf(transition.charAt(3));
            top=String.valueOf(transition.charAt(5));
            next=transition.substring(7,9);
            if(transition.charAt(10)!='E'){
              pushS=transition.substring(10,12);
            }
            else{
              pushS=String.valueOf(transition.charAt(10));
            }
            automata.addTransitioin(old,terminal,top,next,pushS);
            line++;
          }
          System.out.println("=> Transtion State : ");
          automata.showTrantition();
          System.out.println("=> Input the test word : ");
          String testWord=m.next();
          automata.testWordPDA(testWord+"E");
        }
        catch (FileNotFoundException fnfe) {
            // do something sensible with the exception.
        }
    }
}
