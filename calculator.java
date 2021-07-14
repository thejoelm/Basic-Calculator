import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Scanner;

public class calculator {
    private static ArrayList<String> chars = new ArrayList<String>();
    public static ArrayList<String> splitNums = new ArrayList<String>();

    public static void main(String[] args) {
        Scanner readEquation = new Scanner(System.in);
        System.out.println("Please enter an equation using +, -, /, and *");

        String equation = readEquation.nextLine();
        readEquation.close();

        char[] cArray = equation.toCharArray();
        for(int i = 0; i < cArray.length; i++){
                splitNums.add(String.valueOf(cArray[i]));
        }

        String temp = "";
        for(int i = 0; i < splitNums.size(); i++){
            if(!splitNums.get(i).equals("+") && !splitNums.get(i).equals("-") && !splitNums.get(i).equals("*") && !splitNums.get(i).equals("/") && !splitNums.get(i).equals("^")){
                temp += splitNums.get(i);
            }
            else {
                chars.add(temp);
                chars.add(splitNums.get(i));
                temp = "";
            }
        }
        chars.add(temp);

        calculator eCalculator = new calculator();

        double answer = Double.valueOf(eCalculator.total());
        if((answer % 1) == 0){
            int intAns = (int) answer;
            System.out.println(intAns);
        }
        else{
            System.out.println(answer);
        }
    }

    public String total(){

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
}
