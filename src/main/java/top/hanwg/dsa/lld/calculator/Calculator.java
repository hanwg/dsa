package top.hanwg.dsa.lld.calculator;

import java.util.Stack;

public class Calculator {

    Token lastOperatorToken;

    int calculate(String expression) {
        Lexer lexer = new Lexer(expression);

        Stack<Token> tokens = new Stack<>();

        Token token;
        while ((token = lexer.next()) != null) {
            if (token.type() == TokenType.NUMBER) {
                if (tokens.empty()) {
                    tokens.push(token);
                    continue;
                }

                tokens.push(token);
                Token nextToken = lexer.peek();

                if ((nextToken != null && nextToken.type() == TokenType.CLOSE_PARENTHESIS) || getOperatorPrecedence(lastOperatorToken) > getOperatorPrecedence(nextToken)) {
                    eval(tokens);
                }
                continue;
            } else {
                if (token.type() == TokenType.OPEN_PARENTHESIS) {
                    lastOperatorToken = null;
                } else if (token.type() == TokenType.CLOSE_PARENTHESIS) {
                    Token result = tokens.pop();
                    tokens.pop(); // open parenthesis
                    tokens.push(result);
                    continue;
                } else {
                    lastOperatorToken = token;
                }
            }

            // token is operator
            tokens.push(token);
        }

        // get the result
        if (tokens.isEmpty()) {
            return 0;
        }
        token = tokens.pop();
        return Integer.parseInt(token.value());
    }

    int getOperatorPrecedence(Token token) {
        if (token == null) {
            return -1;
        }

        TokenType type = token.type();
        return switch (type) {
            case TokenType.PLUS -> 1;
            case TokenType.MINUS -> 1;
            case TIMES -> 2;
            case DIVIDE -> 2;
            case NUMBER -> 3;
            case OPEN_PARENTHESIS -> 3;
            case CLOSE_PARENTHESIS -> 3;
        };
    }

    void eval(Stack<Token> tokens) {
        while (tokens.size() > 1) {
            Token operand2Token = tokens.pop();
            int operand2 = intValue(operand2Token);

            Token operator = tokens.peek();
            if (operator.type() == TokenType.OPEN_PARENTHESIS) {
                tokens.push(operand2Token);
                return;
            }
            tokens.pop();

            Token operand1Token = tokens.pop();
            int operand1 = intValue(operand1Token);

            int result = 0;
            switch (operator.type()) {
                case TokenType.PLUS -> result = operand1 + operand2;
                case TokenType.MINUS -> result = operand1 - operand2;
                case TokenType.TIMES -> result = operand1 * operand2;
                case TokenType.DIVIDE -> result = operand1 / operand2;
            }

            Token resultToken = new Token(TokenType.NUMBER, String.valueOf(result));
            tokens.push(resultToken);
        }
    }

    int intValue(Token token) {
        String value = token.value();
        return Integer.parseInt(value);
    }

    public static void main(String[] args) {
        Calculator calc = new Calculator();
        System.out.println(calc.calculate("1 *(2+3 )")); // 5
        System.out.println(calc.calculate("1 +2* 3")); // 7
        System.out.println(calc.calculate("1 +2 + 3")); // 6
        System.out.println(calc.calculate("1 +2 ")); // 3
    }
}
