import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import Pilha.*;


public class ShutingYard {


    private static double PI = 3.1415926535898;

    private static boolean isNumber(String S){
        try{
            Double.parseDouble(S);
            return true;
        }catch(Exception e){
            return false;
        }
    }

    private static boolean isOperator(String S){
        return "+".equals(S) ||
                "-".equals(S) ||
                "*".equals(S) ||
                "/".equals(S) ||
                "^".equals(S) ||
                "(".equals(S) ||
                ")".equals(S) ||
                "U".equals(S);
    }

    private static int operatorPrecedence(String S){
        if("(".equals(S))
            return 0;
        else if("+".equals(S) || "-".equals(S))
            return 1;
        else if("*".equals(S) || "/".equals(S))
            return 2;
        else
            return 3;
    }

    private static boolean isFunction(String s){
        return s.length() > 1;
    }

    private static double makeResult(ArrayList<String> s,HashMap<Character,Double> x){

        Pilha<Double> operands = new Pilha<Double>();
        Pilha<String> operators = new Pilha<String>();

        //System.out.println("My Tokens Are: " + s);


        for (String s1 : s) {
           if(isNumber(s1)) {
               operands.push(Double.parseDouble(s1));
           }else if("PI".equals(s1)){
               operands.push(PI);
           }else if (s1.length() == 1) {
               if (!isOperator(s1)) {
                   operands.push(x.get(s1.charAt(0)));
               } else if (s1.charAt(0) == '(') {
                   operators.push(s1);
               } else if (s1.charAt(0) == ')') {
                   while (!(operators.top().charAt(0) == '(')) {
                       operands = applyOperator(operands, operators.pop());
                   }
                   operators.pop();
                   if (isFunction(operators.top())) {
                       operands.push(applyFunction(operands.pop(), operators.pop()));
                   }
               } else if (!operators.isEmpty()) {
                   if (operatorPrecedence(s1) > operatorPrecedence(operators.top()) || isFunction(s1)) {
                       operators.push(s1);
                   } else {
                       while (operatorPrecedence(s1) <= operatorPrecedence(operators.top()) && !operators.isEmpty()) {
                           operands = applyOperator(operands, operators.pop());
                       }
                       operators.push(s1);
                   }
               } else {
                   operators.push(s1);
               }
           }else{
               operators.push(s1);
           }

        }
        while (!operators.isEmpty()){
            operands = applyOperator(operands,operators.pop());
        }
        return operands.top();
    }

    private static Double applyFunction(Double pop, String s) {

        if (s.equals("sin")) {
            return Math.sin(pop);
        } else if (s.equals("cos")) {
            return Math.cos(pop);
        } else if (s.equals("sqrt")) {
            return Math.sqrt(pop);
        } else if (s.equals("exp")) {
            return Math.exp(pop);
        } else if (s.equals("log")) {
            return Math.log10(pop);
        } else if (s.equals("tan")) {
            return Math.tan(pop);
        } else if (s.equals("ln")) {
            return Math.log(pop);
        }else if(s.equals("abs"))
            return Math.abs(pop);
        return null;
    }

    private static String operatorAssociativy(String S){
        if("U".equals(S)){
            return "unary";
        }
        return "binary";
    }

    private static Pilha<Double> applyOperator(Pilha<Double> operands, String pop) {
        if(operatorAssociativy(pop).equals("binary")){
            operands.push(applyBinaryOperator(operands.pop(),operands.pop(),pop));
        }else
            operands.push(operands.pop() * -1);
        return operands;
    }

    private static Double applyBinaryOperator(Double aDouble, Double aDouble1, String pop) {
        double result = 0;
        if (pop.equals("+")) {
            result = aDouble1 + aDouble;
        }else if(pop.equals("-"))
            result = aDouble1 - aDouble;
        else if(pop.equals("*"))
            result = aDouble1 * aDouble;
        else if(pop.equals("/"))
            result = aDouble1 / aDouble;
        else if(pop.equals("^"))
            result = Math.pow(aDouble1,aDouble);
        return result;
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
            }else if(ch >= '0' && ch <= '9'){
                chString = chString.append(String.valueOf(ch));
            } else {
                if (chString.length() > 0) {
                    arrL.add(chString.toString());
                    chString = new StringBuilder();
                }
                arrL.add(String.valueOf(ch));
            }
        }
        if(!"".equals(chString.toString()))
            arrL.add(chString.toString());
        ArrayList<String> arrR = new ArrayList<String>();
        String Prev = "";
        for (String s : arrL) {
            if(isOperator(Prev) && "-".equals(s)){
                s = "U";
            }
            arrR.add(s);
            Prev = s;
        }
        return arrR;
    }

    private static HashMap<Character, Double> getOperands(ArrayList<String> split){

        Scanner scanner = new Scanner(System.in);
        HashMap<Character,Double> operands = new HashMap<Character, Double>();

        for(String s:split){
            if(s.length() == 1 && !isNumber(s) && !isOperator(s) && !operands.containsKey(s.charAt(0)) && !s.equals(" ")){
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


        System.out.println(makeResult(splitExp(input),getOperands(splitExp(input))));

    }

}
