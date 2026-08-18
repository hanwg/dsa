package top.hanwg.dsa.lld.calculator;

public class Lexer {

    int index = 0;
    StringBuilder input;

    public Lexer(String input) {
        this.input = new StringBuilder(input);
    }

    public Token next() {
        Token token = peek();

        if (token == null) {
            return null;
        }

        String value = token.value();
        index += value.length();
        return token;
    }

    public Token peek() {
        if (index >= input.length()) {
            return null;
        }

        char c = input.charAt(index);

        // ignore whitespace
        while (Character.isWhitespace(c)) {
            index++;
            if (index >= input.length()) {
                break;
            }
            c = input.charAt(index);
        }

        switch (c) {
            case '+':
                return new Token(TokenType.PLUS, String.valueOf(c));
            case '-':
                return new Token(TokenType.MINUS, String.valueOf(c));
            case '*':
                return new Token(TokenType.TIMES, String.valueOf(c));
            case '/':
                return new Token(TokenType.DIVIDE, String.valueOf(c));
            case '(':
                return new Token(TokenType.OPEN_PARENTHESIS, String.valueOf(c));
            case ')':
                return new Token(TokenType.CLOSE_PARENTHESIS, String.valueOf(c));
            case ' ':
                return null;
            default:
                if (Character.isDigit(c)) {
                    int start = index;
                    int end = index;

                    do {
                        end++;

                        if (end >= input.length()) {
                            break;
                        }

                        c = input.charAt(end);
                    } while (Character.isDigit(c));

                    return new Token(TokenType.NUMBER, input.substring(start, end));
                }

                throw new IllegalStateException();
        }
    }
}
