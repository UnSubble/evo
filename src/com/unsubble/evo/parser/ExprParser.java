package com.unsubble.evo.parser;

import com.unsubble.evo.ast.AstNode;
import com.unsubble.evo.ast.node.BinaryOpNode;
import com.unsubble.evo.ast.node.ExprNode;
import com.unsubble.evo.ast.node.IdentifierNode;
import com.unsubble.evo.token.TokenStream;
import com.unsubble.evo.token.TokenType;

public class ExprParser {
    private final TokenStream stream;

    public ExprParser(TokenStream stream) {
        this.stream = stream;
    }

    public AstNode parse() {
        return parseExpression();
    }

    private AstNode parseExpression() {
        AstNode left = parseTerm();
        while (stream.match(TokenType.PLUS, TokenType.MINUS)) {
            String op = stream.consume().value();
            AstNode right = parseTerm();
            left = new BinaryOpNode(left, op, right);
        }
        return left;
    }

    private AstNode parseTerm() {
        AstNode left = parseFactor();
        while (stream.match(TokenType.STAR, TokenType.SLASH)) {
            String op = stream.consume().value();
            AstNode right = parseFactor();
            left = new BinaryOpNode(left, op, right);
        }
        return left;
    }

    private AstNode parseFactor() {
        if (stream.match(TokenType.LPAREN)) {
            stream.consume();
            AstNode node = parseExpression();
            stream.expect(TokenType.RPAREN);
            return node;
        } else if (stream.match(TokenType.IDENTIFIER)) {
            return new IdentifierNode(stream.consume().value());
        } else if (stream.match(TokenType.NUMBER)) {
            return new ExprNode(stream.consume().value());
        } else {
            throw new RuntimeException("Unexpected token in expression: " + stream.peek());
        }
    }
}
