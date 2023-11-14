package calculator;

import java.util.Stack;

public class CalculatorImpl implements Calculator {

	@Override
	public double calculate(String formula) {
		Stack<Double> stack = new Stack<>();
		String[] tokens = formula.split(" ");
		String operatorToken = null;

		for (String token : tokens) {
			if (isNumber(token)) {
				stack.push(Double.parseDouble(token));
			} else if (isOperator(token)) {
				operatorToken = token;
			}

			if (stack.size() > 1 && operatorToken != null) {
				double operand2 = stack.pop();
				double operand1 = stack.pop();
				double result = performOperation(operatorToken, operand1, operand2);
				stack.push(result);
			}
		}

		try {
			return stack.pop();
		} catch (Exception e) {
			return 0;
		}
	}

	private boolean isNumber(String token) {
		try {
			Double.parseDouble(token);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	private boolean isOperator(String token) {
		return token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/");
	}

	private double performOperation(String operator, double operand1, double operand2) {
		switch (operator) {
		case "+":
			return operand1 + operand2;
		case "-":
			return operand1 - operand2;
		case "*":
			return operand1 * operand2;
		case "/":
			return operand1 / operand2;
		default:
			throw new IllegalArgumentException("Invalid operator: " + operator);
		}
	}
}