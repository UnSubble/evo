package test.com.unsubble.lexer;

import com.unsubble.evo.common.Holder;
import com.unsubble.evo.lexer.Lexer;
import com.unsubble.evo.token.Token;
import com.unsubble.evo.token.TokenType;

import java.util.Arrays;
import java.util.List;

public class TokenizeTest {

    private Token[] tokenize(String... tokenPairStrings) {
        Token[] arr = new Token[tokenPairStrings.length / 2];
        for (int i = 0; i < tokenPairStrings.length / 2; i++) {
            arr[i] = new Token(TokenType.valueOf(tokenPairStrings[i * 2].toUpperCase()), tokenPairStrings[i * 2 + 1]);
        }
        return arr;
    }

    private Token[] tokenize(Object... pairs) {
        Token[] arr = new Token[pairs.length / 2];
        for (int i = 0; i < pairs.length / 2; i++) {
            arr[i] = new Token((TokenType) pairs[2 * i], (String) pairs[2 * i + 1]);
        }
        return arr;
    }

    public void testMathExpressionSyntax() {
        String src = """
                x = 5 + 8 * -(4 / 2 % 3)""";
        List<Token> tokens = Arrays.stream(tokenize(TokenType.IDENTIFIER, "x", TokenType.ASSIGN, "=",
                TokenType.NUMBER, "5", TokenType.PLUS, "+", TokenType.NUMBER, "8", TokenType.STAR, "*",
                TokenType.MINUS, "-", TokenType.LPAREN, "(", TokenType.NUMBER, "4", TokenType.SLASH, "/",
                TokenType.NUMBER, "2", TokenType.MOD, "%", TokenType.NUMBER, "3",
                TokenType.RPAREN, ")", TokenType.EOF, "EOF")).toList();
        Holder<List<Token>> holder = new DummyHolder();
        new Lexer(src).lex(holder);
        assert tokens.equals(holder.get());
    }

    public void testFunctionCallSyntax() {
        String src = """
                x = fn(8, 3);""";
        List<Token> tokens = Arrays.stream(tokenize(TokenType.IDENTIFIER, "x", TokenType.ASSIGN, "=",
                TokenType.IDENTIFIER, "fn", TokenType.LPAREN, "(", TokenType.NUMBER, "8", TokenType.COMMA, ",",
                TokenType.NUMBER, "3", TokenType.RPAREN, ")", TokenType.SEMICOLON, ";", TokenType.EOF, "EOF")).toList();
        Holder<List<Token>> holder = new DummyHolder();
        new Lexer(src).lex(holder);
        assert tokens.equals(holder.get());
    }

    public void testIfStatementSyntax() {
        String src = """
                if (x == 5) {
                    return 1;
                }""";
        List<Token> tokens = Arrays.stream(tokenize(TokenType.IF, "if", TokenType.LPAREN, "(", TokenType.IDENTIFIER, "x",
                TokenType.EQUAL, "==", TokenType.NUMBER, "5", TokenType.RPAREN, ")", TokenType.LBRACE, "{", TokenType.RETURN, "return",
                TokenType.NUMBER, "1", TokenType.SEMICOLON, ";", TokenType.RBRACE, "}", TokenType.EOF, "EOF")).toList();
        Holder<List<Token>> holder = new DummyHolder();
        new Lexer(src).lex(holder);
        assert tokens.equals(holder.get());
    }

    public void testTypeDeclarationSyntax() {
        String src = """
                type MyType(5, 1: a, 4: b);""";
        List<Token> tokens = Arrays.stream(tokenize(TokenType.TYPE, "type", TokenType.IDENTIFIER, "MyType",
                TokenType.LPAREN, "(", TokenType.NUMBER, "5", TokenType.COMMA, ",", TokenType.NUMBER, "1",
                TokenType.COLON, ":", TokenType.IDENTIFIER, "a", TokenType.COMMA, ",", TokenType.NUMBER, "4",
                TokenType.COLON, ":", TokenType.IDENTIFIER, "b", TokenType.RPAREN, ")", TokenType.SEMICOLON, ";",
                TokenType.EOF, "EOF")).toList();
        Holder<List<Token>> holder = new DummyHolder();
        new Lexer(src).lex(holder);
        assert tokens.equals(holder.get());
    }
}
