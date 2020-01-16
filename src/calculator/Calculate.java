package calculator;

import java.util.ArrayList;

public class Calculate {

    private Float Result;
    private String a, b;
    private static String c;
    private static String operation;
    private static ArrayList<String> log = new ArrayList<String>();

    public String calculate(String operator, Float num1, Float num2) {

        switch (operator) {
        case "+":
            Result = num1 + num2;
            break;
        case "-":
            Result = num1 - num2;
            break;
        case "*":
            Result = num1 * num2;
            break;
        case "/":
            Result = num1 / num2;
            break;
        }
        if (Math.floor(Result) == Result) {
            c = Result.toString();
            c = c.substring(0, c.length() - 2);
        } else {
            c = Result.toString();
        }
        return c;
    }

    public String getOperation(String operator, Float num1, Float num2) {

        if (Math.floor(num1) == num1 && Math.floor(num2) == num2) {

            a = num1.toString();
            a = a.substring(0, a.length() - 2);
            b = num2.toString();
            b = b.substring(0, b.length() - 2);

        } else if (Math.floor(num1) == num1 && Math.floor(num2) != num2) {

            a = num1.toString();
            a = a.substring(0, a.length() - 2);
            b = num2.toString();

        } else if (Math.floor(num1) != num1 && Math.floor(num2) == num2) {

            a = num1.toString();
            b = num2.toString();
            b = b.substring(0, b.length() - 2);
        } else if (Math.floor(num1) != num1 && Math.floor(num2) != num2) {

            a = num1.toString();
            b = num2.toString();
        }
        operation = a + operator + b;
        log.add(operation + "=" + c);
        return operation;
    }

    public ArrayList<String> getLog() {
        return log;
    }
}