
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Calculator {
    public static void main(String[] args)throws NumberFormatException {
        Scanner scanner = new Scanner(System.in);
        String expression = scanner.nextLine();
        String[] arrExpression = expression.split("[+,\\/ , * ,-]");
        Result getOperand = getOperand(expression);
        String[] arrRomeNumber = new String[]{"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X","L","C"};
        String[] arrNumber = getArrNumber();
        int a, b;
        char operand = getOperand.arrOperand()[0].charAt(0);
try {
    boolean roman=checkRomeNumber(arrRomeNumber,arrExpression);
    if (roman)romanNumToNum(arrRomeNumber, arrNumber, arrExpression);

    a = Integer.parseInt(arrExpression[0]);
    b = Integer.parseInt(arrExpression[1]);

    if (getOperand.array().size() == 1){
        if (a > 0 && a <= 10 && b > 0 && b <= 10) {
            int answer = calculation(a, b, operand);
            if (roman){
                if (a<=b&&operand=='-'){
                    System.out.println("throws Exception //т.к. в римской системе нет отрицательных чисел и нолей");
                }else {
                    System.out.println(expression + " = " + numToRomanNum(arrRomeNumber, arrNumber, answer));
                }
            }else {
                System.out.println(expression + " = " + answer);
            }
        } else {
            System.out.println("throws exception// т.к. операнды не могут быть меньше ноля и болше десяти");
        }
}else{
        System.out.println("throws exception// т.к. формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        }
}catch (Exception e) {
    System.out.println(e.getMessage());
    if (!checkRomeNumber(arrRomeNumber,arrExpression))System.out.print("throws Exception //т.к. используются одновременно разные системы счисления");
}
    }

    private static Result getOperand(String expression) {
        String[] arrOperand = expression.split("(\\w)");
        List<String> array = new ArrayList<>();
        for (int i = 0; i < arrOperand.length; i++) {
            if (!arrOperand[i].equals("") && arrOperand.length > 0) array.add(arrOperand[i]);
        }
        arrOperand = array.toArray(new String[0]);
        Result getOperand = new Result(arrOperand, array);
        return getOperand;
    }

    private record Result(String[] arrOperand, List<String> array) {
    }

    private static String[] getArrNumber() {
        String[] arrNumber = new String[10];
        for (int i = 0; i < arrNumber.length; i++) {
            arrNumber[i]=Integer.toString(i+1);
        }
        return arrNumber;
    }

    private static void romanNumToNum(String[] arrRomeNumber, String[] arrNumber, String[] arrExpression) throws NumberFormatException {
               for (int i = 0; i < arrExpression.length; i++) {
                   for (int j = 0; j < arrRomeNumber.length; j++) {
                       if (arrRomeNumber[j].equals(arrExpression[i])) {
                           arrExpression[i] = arrNumber[j];
                           break;
                       }
                   }
               }

    }

    private static String numToRomanNum(String[] arrRomeNumber, String[] arrNumber, int answer) {
        List<Integer> digit = new ArrayList<>();
        while (answer > 0) {
            digit.add(0, answer % 10);
            answer /= 10;
        }
        List<String> romanAnswer = new ArrayList<>();
        if (digit.get(digit.size() - 1) != 0) {
            romanAnswer.add(arrRomeNumber[digit.get(digit.size() - 1) - 1]);
        }
if (digit.size()==2) {
    if (digit.get(digit.size() - 2) < 4) {
        for (int k = 0; k < digit.get(digit.size() - 2); k++) {
            romanAnswer.add(0, arrRomeNumber[9]);
        }
    }
    if (digit.get(digit.size() - 2) >= 4 && digit.get(digit.size() - 2) < 9) {
        if (digit.get(digit.size() - 2) == 4) {
            romanAnswer.add(0, arrRomeNumber[9] + arrRomeNumber[10]);
        } else {

            for (int i = 5; i < digit.get(digit.size() - 2); i++) {
                romanAnswer.add(0, arrRomeNumber[9]);
            }
            romanAnswer.add(0, arrRomeNumber[10]);
        }
    }
    if (digit.get(digit.size() - 2) >= 9) {
        romanAnswer.add(0, arrRomeNumber[9] + arrRomeNumber[11]);
    }
}
        if (digit.size()==3){
            if (digit.get(digit.size() - 3) == 1) romanAnswer.add(arrRomeNumber[11]);
        }

        StringBuilder builder= new StringBuilder();
        int i = 0;
        while (i< romanAnswer.size()){
            builder.append(romanAnswer.get(i));
            i++;
        }
        String str = builder.toString();
        return str;
    }

    static boolean checkRomeNumber(String[]arrRomeNumber, String[] arrExpression){
        int count = 0;
        for (int j = 0; j < arrExpression.length; j++) {
            for (int i = 0; i < arrRomeNumber.length; i++) {
                if (arrRomeNumber[i].equals(arrExpression[count])) {
                    count++;
                    if (count == arrExpression.length) return true;
                }
            }
        }
        return false;
    }

    private static int calculation(int a, int b, char operand) {
        int answer = 0;
        switch (operand){
            case('+'):
                answer = a + b;
                break;
            case('-'):
                answer = a - b;
                break;
            case('*'):
                answer = a * b;
                break;
            case('/'):
                answer = a / b;
                break;
        }return answer;
    }
}
