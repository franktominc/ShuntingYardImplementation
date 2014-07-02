import java.util.HashMap;
import java.util.Scanner;

import static java.lang.Integer.*;

/**
 * Created by frank on 7/2/2014.
 */
public class ShutingYard {

    public static boolean isNumber(String S){
        try{
            Integer.parseInt(S);
            return true;
        }catch(Exception e){
            return false;
        }
    }
    public static void main(String[] args) {
        HashMap<Character,Integer> precedences = new HashMap<Character, Integer>();
        precedences.put('+',1);
        precedences.put('-',1);
        precedences.put('*',2);
        precedences.put('/',2);
        precedences.put('^',3);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Entre com a expressão a ser avaliada");
        String input = scanner.nextLine();


        String[] split = input.split("\\s|\\(|\\)|\\+|\\-|\\*|/");



        HashMap<Character,Double> variables = new HashMap<Character, Double>();

        for(String s:split){

            if(s.length() == 1 && !isNumber(s) && !precedences.containsKey(s.charAt(0))){
                System.out.println("Entre com o valor da variavel " + s);
                variables.put(s.charAt(0),scanner.nextDouble());
            }
        }
        System.out.println("Variaveis " + variables.keySet().toString());
        System.out.println("Valores " + variables.values().toString());

    }
}
