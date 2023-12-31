package calculator;

import java.util.Stack;
import java.util.StringTokenizer;

public class CalculatorImpl implements Calculator {

//	This method is the entry point for calculating a mathematical expression.
//	It calls the evaluateExpression method and catches any exceptions,
//	returning 0 if an exception occurs.
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

// This method takes an infix mathematical expression as input,
// converts it to postfix notation using the infixToPostfix method.
// For example: the equation is 4 + 3 * 2 so it will return -> 4 3 2 * +
	private static String infixToPostfix(String infix) {
		StringBuilder postfix = new StringBuilder();
		Stack<Character> operatorStack = new Stack<>();

		StringTokenizer tokenizer = new StringTokenizer(infix, "+-*/^sqrt()", true);
		boolean wasPreviousTokenOperator = true;

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
//				And in this section it will check out the precedence of the operator,
//				so that it put the highest precedence get pushed first
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
			wasPreviousTokenOperator = isOperator(firstChar) || firstChar == '(';
		}

		while (!operatorStack.isEmpty()) {
			postfix.append(operatorStack.pop()).append(" ");
		}

		return postfix.toString().trim();
	}

//	This method converts an infix mathematical expression to postfix notation.
//	It uses a Stack (operatorStack) to keep track of operators while traversing the input expression.
	private static double evaluatePostfix(String postfix) {
		Stack<Double> operandStack = new Stack<>();

		StringTokenizer tokenizer = new StringTokenizer(postfix);
		while (tokenizer.hasMoreTokens()) {
			String token = tokenizer.nextToken();

			if (isNumeric(token)) {
				operandStack.push(Double.parseDouble(token));
			} else if (isOperator(token.charAt(0))) {
				double operand2 = (!operandStack.isEmpty()) ? operandStack.pop() : 0;
				double operand1 = (token.charAt(0) == 's') ? 0 : (!operandStack.isEmpty()) ? operandStack.pop() : 1;
				operandStack.push(performOperation(operand1, operand2, token.charAt(0)));
			}
		}

		return operandStack.pop();
	}

//	isNumeric(String str): Checks if a string represents a numeric value.
	private static boolean isNumeric(String str) {
		try {
			Double.parseDouble(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

//	isOperator(char ch): Checks if a character is an operator (+, -, *, /, s for square root, ^ for exponentiation).
	private static boolean isOperator(char ch) {
		return ch == '+' || ch == '-' || ch == '*' || ch == '/' || ch == 's' || ch == '^';
	}

//	precedence(char operator): Assigns precedence values to operators.
	private static int precedence(char operator) {
		switch (operator) {
			case '+':
			case '-':
				return 1;
			case '*':
			case '/':
				return 2;
			case '^':
			case 's':
				return 3;
			default:
				return 0;
		}
	}

//	performOperation(double operand1, double operand2, char operator): Performs the specified operation based on the operator.
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
			case 's':
				return Math.sqrt(operand2);
			case '^':
				return Math.pow(operand1, operand2);
			default:
				throw new IllegalArgumentException("Invalid operator: " + operator);
		}
	}
}