package calculator;

import java.util.Stack;
import java.util.StringTokenizer;

public class CalculatorImpl implements Calculator {

	@Override
	public double calculate(String formula) {
		double result = evaluateExpression(formula);
		try {
			return result;
		} catch (Exception e) {
			return 0;
		}
	}

	public static double evaluateExpression(String expression) {
		String postfix = infixToPostfix(expression);
		return evaluatePostfix(postfix);
	}

	private static String infixToPostfix(String infix) {
		StringBuilder postfix = new StringBuilder();
		Stack<Character> operatorStack = new Stack<>();

		StringTokenizer tokenizer = new StringTokenizer(infix, "+-*/()", true);
		boolean wasPreviousTokenOperator = true;
		boolean wasPreviousTokenOpeningParenthesis = false;

		while (tokenizer.hasMoreTokens()) {
			String token = tokenizer.nextToken().trim();

			if (token.isEmpty()) {
				continue;
			}

			char firstChar = token.charAt(0);

			if (firstChar == '-' && wasPreviousTokenOperator){
				postfix.append(firstChar);
			} else if (Character.isDigit(firstChar)) {
				postfix.append(token).append(" ");
			} else if (isOperator(firstChar)) {
				while (!operatorStack.isEmpty() && operatorStack.peek() != '(' && precedence(operatorStack.peek()) >= precedence(firstChar)) {
					postfix.append(operatorStack.pop()).append(" ");
				}
				operatorStack.push(firstChar);
			} else if (firstChar == '(') {
				operatorStack.push(firstChar);
			} else if (firstChar == ')') {
				while (!operatorStack.isEmpty() && operatorStack.peek() != '(') {
					postfix.append(operatorStack.pop()).append(" ");
				}
				if (!operatorStack.isEmpty() && operatorStack.peek() == '(') {
					operatorStack.pop();
				}
			}

			// Append unary minus directly without a space before it
//			if (firstChar == '-' && (wasPreviousTokenOperator || wasPreviousTokenOpeningParenthesis)) {
//				postfix.append(firstChar);
//			}

			wasPreviousTokenOperator = isOperator(firstChar) || firstChar == '(';
			wasPreviousTokenOpeningParenthesis = firstChar == '(';
		}

		while (!operatorStack.isEmpty()) {
			postfix.append(operatorStack.pop()).append(" ");
		}

		return postfix.toString().trim();
	}

	private static double evaluatePostfix(String postfix) {
		Stack<Double> operandStack = new Stack<>();

		StringTokenizer tokenizer = new StringTokenizer(postfix);
		while (tokenizer.hasMoreTokens()) {
			String token = tokenizer.nextToken();

			if (isNumeric(token)) {
				operandStack.push(Double.parseDouble(token));
			} else if (isOperator(token.charAt(0))) {
				double operand2 = operandStack.pop();
				double operand1 = operandStack.pop();
				operandStack.push(performOperation(operand1, operand2, token.charAt(0)));
			}
		}

		return operandStack.pop();
	}

	private static boolean isNumeric(String str) {
		try {
			Double.parseDouble(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	private static boolean isOperator(char ch) {
		return ch == '+' || ch == '-' || ch == '*' || ch == '/';
	}

	private static int precedence(char operator) {
		switch (operator) {
			case '+':
			case '-':
				return 1;
			case '*':
			case '/':
				return 2;
			default:
				return 0;
		}
	}

	private static double performOperation(double operand1, double operand2, char operator) {
		switch (operator) {
			case '+':
				return operand1 + operand2;
			case '-':
				return operand1 - operand2;
			case '*':
				return operand1 * operand2;
			case '/':
				if (operand2 == 0) {
					throw new ArithmeticException("Division by zero");
				}
				return operand1 / operand2;
			default:
				throw new IllegalArgumentException("Invalid operator: " + operator);
		}
	}
}