import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Scanner;

public class calculator {
    private static ArrayList<String> allChars = new ArrayList<String>();
    public static ArrayList<String> splitNums = new ArrayList<String>();
    public static ArrayList<String> test = new ArrayList<String>();

    public static void main(String[] args) {
        Scanner readEquation = new Scanner(System.in);
        System.out.println("Please enter an equation using +, -, /, *, or ()");

        String equation = readEquation.nextLine();
        readEquation.close();

        char[] cArray = equation.toCharArray();
        for(int i = 0; i < cArray.length; i++){
                splitNums.add(String.valueOf(cArray[i]));
        }

        System.out.println(splitNums);

        String temp = "";
        for(int i = 0; i < splitNums.size(); i++){
            if(!splitNums.get(i).equals("+") && !splitNums.get(i).equals("-") && !splitNums.get(i).equals("*") && !splitNums.get(i).equals("/") && !splitNums.get(i).equals("^") && !splitNums.get(i).equals("(") && !splitNums.get(i).equals(")")){
                temp += splitNums.get(i);
            }
            else {
                if(!temp.equals("")){
                    allChars.add(temp);
                }
                    allChars.add(splitNums.get(i));
                    temp = "";
            }
        }
        if(!temp.equals("")){
            allChars.add(temp);
        }

        calculator eCalculator = new calculator();

        double answer = Double.valueOf(eCalculator.total(allChars));
        if((answer % 1) == 0){
            int intAns = (int) answer;
            System.out.println("Answer: " + intAns);
        }
        else{
            
            System.out.println("Answer: " + eCalculator.round(answer, 3));
        }
    }

    public String parentheses(ArrayList<String> chars){
        
        int first = findFirstParentheses(chars);
        int last = findLastParentheses(chars);

        ArrayList<String> insideEquation = new ArrayList<String>();
        for(int i = first + 1; i < last; i++){
            insideEquation.add(chars.get(i));
        }
        String answerPara = total(insideEquation);

        return answerPara;
    }

    public String total(ArrayList<String> chars){

        boolean hasParentheses = false;

        for(int i = 0; i < chars.size(); i++){

            if(chars.get(i).equals("(")){
                hasParentheses = true;
                break;
            }
        }

        if(hasParentheses == true){
            int first = findFirstParentheses(chars);
            int last = findLastParentheses(chars);

            chars.set(first, parentheses(chars));

            for(int i = 0; i < last - first; i++){
                chars.remove(first + 1);
            }
        }

        for(int i = 1; i < chars.size() - 1; i++){

            if(chars.get(i).equals("^")){
                double first = Double.parseDouble(chars.get(i - 1));
                double second = Double.parseDouble(chars.get(i + 1));

                double exp = Math.pow(first, second);
                chars.set(i - 1, String.valueOf(round(exp, 3)));
                chars.remove(i);
                chars.remove(i);
                if(chars.size() == 1){
                    return chars.get(0);
                }
                i--;
            }
        }
        for(int i = 1; i < chars.size() - 1; i++){

            if(chars.get(i).equals("/")){
                double first = Double.parseDouble(chars.get(i - 1));
                double second = Double.parseDouble(chars.get(i + 1));

                double div = first / second;
                chars.set(i - 1, String.valueOf(round(div, 3)));
                chars.remove(i);
                chars.remove(i);
                if(chars.size() == 1){
                    return chars.get(0);
                }
                i--;
            }

            else if(chars.get(i).equals("*")){
                double first = Double.parseDouble(chars.get(i - 1));
                double second = Double.parseDouble(chars.get(i + 1));

                double mult = first * second;
                chars.set(i - 1, String.valueOf(mult));
                chars.remove(i);
                chars.remove(i);
                if(chars.size() == 1){
                    return chars.get(0);
                }
                i--;
            }
        }

        for(int i = 1; i < chars.size() - 1; i++){
            
            if(chars.get(i).equals("+")){
                double first = Double.parseDouble(chars.get(i - 1));
                double second = Double.parseDouble(chars.get(i + 1));

                double sum = first + second;
                chars.set(i - 1, String.valueOf(sum));
                chars.remove(i);
                chars.remove(i);
                if(chars.size() == 1){
                    return chars.get(0);
                }
                i--;
            }

            else if(chars.get(i).equals("-")){
                double first = Double.parseDouble(chars.get(i - 1));
                double second = Double.parseDouble(chars.get(i + 1));

                double diff = first - second;
                chars.set(i - 1, String.valueOf(diff));
                chars.remove(i);
                chars.remove(i);
                if(chars.size() == 1){
                    return chars.get(0);
                }
                i--;
            }
        }

        return chars.get(0);
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(Double.toString(value));
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        
        return bd.doubleValue();
    }

    public int findFirstParentheses(ArrayList<String> chars){
        boolean found = false;
        int firstOccurence = 0;

        for(int i = 0; i < chars.size(); i++){
            if(chars.get(i).equals("(")){
                if(found == false){
                    firstOccurence = i;
                    found = true;
                    break;
                }
            }
        }
        return firstOccurence;
    }

    public int findLastParentheses(ArrayList<String> chars){
        boolean found = false;
        int lastOccurence = 0;
        for(int i = chars.size() - 1; i >= 0; i--){
            if(chars.get(i).equals(")")){
                if(found == false){
                    lastOccurence = i;
                    found = true;
                    break;
                }
            }
        }
        return lastOccurence;
    }
}
