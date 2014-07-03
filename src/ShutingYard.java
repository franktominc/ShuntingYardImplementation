import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
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
                "^".equals(S) ||
                "(".equals(S) ||
                ")".equals(S);
    }

    public static int operatorPrecedence(String S){
        if("(".equals(S)){
            return 10;
        }else if("+".equals(S) || "-".equals(S))
            return 1;
        else if("*".equals(S) || "/".equals(S))
            return 2;
        else if(S.length() > 1){
            return 4;
        }else
            return 3;
    }

    public static boolean isFunction(String s){
        return s.length() > 1;
    }

    public static double makeResult(ArrayList<String> s,HashMap<Character,Double> x){

        Pilha<Double> operands = new Pilha<Double>();
        Pilha<String> operators = new Pilha<String>();

        for (String s1 : s) {
            if(s1.length() == 1 && !isNumber(s1) && !isOperator(s1)){
                operands.push(Double.parseDouble(x.get(s1.charAt(0)).toString()));
            }else if(isNumber(s1)){
                operands.push(Double.parseDouble(s1));
            }else if(operators.getK() == 0 ){

            }

        }
        return 0.0;
    }

    private static ArrayList<String> splitExp(String exp) {
        StringBuilder chString = new StringBuilder();
        ArrayList<String> arrL = new ArrayList<String>();


        for (int i = 0 ; i < exp.length() ; i++ ) {

            char ch = exp.charAt(i);

            if(ch == ' ')
                continue;

            if(( ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z')) {
                chString = chString.append(String.valueOf(ch));
            }
            else {
                if (chString.length() > 0) {
                    arrL.add(chString.toString());
                    chString = new StringBuilder();
                }
                arrL.add(String.valueOf(ch));
            }
        }
        arrL.add(chString.toString());
        return arrL;
    }

    private static HashMap<Character, Double> getOperands(String exp){

        Scanner scanner = new Scanner(System.in);
        HashMap<Character,Double> operands = new HashMap<Character, Double>();

        String[] split = exp.split("\\s |" + //Espaços em Branco
                "\\(|" + // Abertura de Parenteses
                "\\)|" + // Fechamento de Parenteses
                "\\+|" + // Soma
                "\\-|" + // Subtração
                "\\*|" + // Multiplicação
                "/|" +   // Divisao
                "\\^");  // Exponenciaçao

        for(String s:split){
            System.out.println(s+ " " + isOperator(s));
            if(s.length() == 1 && !isNumber(s) && !isOperator(s) && !operands.containsKey(s)){
                System.out.println("Entre com o valor da variavel " + s);
                operands.put(s.charAt(0),scanner.nextDouble());
            }
        }
        return operands;
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Entre com a expressão a ser avaliada");
        String input = scanner.nextLine();


        System.out.println(makeResult(splitExp(input),getOperands(input)));

    }
}
