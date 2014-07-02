import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Pattern;

import Pilha.*;
/**
 * Created by frank on 7/2/2014.
 */
public class ShutingYard {


    static double PI = 3.1415926535898;

    public static boolean isNumber(String S){
        try{
            Double.parseDouble(S);
            return true;
        }catch(Exception e){
            return false;
        }
    }

    public static boolean isOperator(String S){
        return "+".equals(S) ||
                "-".equals(S) ||
                "*".equals(S) ||
                "/".equals(S) ||
                "^".equals(S);
    }

    public static int operatorPrecedence(String S){
        if("+".equals(S) || "-".equals(S))
            return 1;
        else if("*".equals(S) || "/".equals(S))
            return 2;
        else
            return 3;
    }

    public static double makeResult(String s,HashMap<Character,Double> x){
        if(s != null && !"".equals(s)){
            String[] split = s.split("");
            for (String s1 : split) {
                System.out.println(s1);
            }

        }
        return 0.0;
    }
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Entre com a expressão a ser avaliada");
        String input = scanner.nextLine();


        Pattern pattern = Pattern.compile("[a-z]+(\\((?>[^()]+|\\( ))* \\)|[a-z]+| \\d+|/|\\-|\\*|\\+");

        String[] split = input.split("\\s|\\(|\\)|\\+|\\-|\\*|/|\\^");


        HashMap<Character,Double> variables = new HashMap<Character, Double>();

        for(String s:split){
            System.out.println(s+ " " + isOperator(s));
            if(s.length() == 1 && !isNumber(s) && !isOperator(s)){
                System.out.println("Entre com o valor da variavel " + s);
                variables.put(s.charAt(0),scanner.nextDouble());
            }

        }

        System.out.println("Variaveis " + variables.keySet().toString());
        System.out.println("Valores " + variables.values().toString());

        split = input.split(pattern.pattern());

        for (String s : split) {
            System.out.println(s);
        }
        //makeResult(input,variables);

    }
}
