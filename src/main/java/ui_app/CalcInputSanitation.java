package ui_app;

import calculator.Calculator;
import calculator.CalculatorImpl;

public class CalcInputSanitation {

    Calculator calculator = new CalculatorImpl(); // Stores call for main calculation functions.

    public String sanitise(String input) {

        try {
            double result = calculator.calculate(input); // Calls main calculation function.
            return String.valueOf(result); // Converts <Double> result to <String>.
        } catch (Exception e) {
            return "ILL_OPERATION"; // Faux/Pseudo Error Handling.
                                    // Not real exception handling, but it works for now.
        }

    }

}
