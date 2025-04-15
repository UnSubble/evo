package test.com.unsubble.main;

import test.com.unsubble.lexer.TokenizeTest;

public class Main {
    public static void main(String[] args) {
        new TokenizeTest().testMathExpressionSyntax();
        new TokenizeTest().testFunctionCallSyntax();
        new TokenizeTest().testIfStatementSyntax();
        new TokenizeTest().testTypeDeclarationSyntax();
    }
}
