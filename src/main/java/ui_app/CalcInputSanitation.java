package ui_app;

import calculator.Calculator;
import calculator.CalculatorImpl;

public class CalcInputSanitation {

    Calculator calculator = new CalculatorImpl();

    public String sanitise(String input) {

        try {
            double result = calculator.calculate(input);
            return String.valueOf(result);
        } catch (Exception e) {
            return "ILL_OPERATION";
        }

    }

}
