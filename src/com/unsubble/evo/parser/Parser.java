package com.unsubble.evo.parser;

import com.unsubble.evo.ast.node.TypeDefNode;
import com.unsubble.evo.common.Twin;
import com.unsubble.evo.token.Token;
import com.unsubble.evo.token.TokenType;
import com.unsubble.evo.ast.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Parser {
    private final List<Token> tokens;
    private int index = 0;

    public Parser(List<Token> tokens) {
        this.tokens = tokens;
    }

    private Token peek() {
        return tokens.get(index);
    }

    private Token consume() {
        return tokens.get(index++);
    }

    private Token expect(TokenType type) {
        Token token = consume();
        if (token.type() != type) {
            throw new RuntimeException("Expected: " + type + ", actual: " + token.type());
        }
        return token;
    }

    public AstNode parseTypeDef() {
        expect(TokenType.TYPE);

        String name = expect(TokenType.IDENTIFIER).value();
        expect(TokenType.LPAREN);

        int totalSize = 0;
        Map<String, Twin<Integer>> fields = new LinkedHashMap<>();

        while (peek().type() != TokenType.RPAREN) {
            int size = Integer.parseInt(expect(TokenType.NUMBER).value());

            if (peek().type() == TokenType.COLON) {
                consume();
                String fieldName = expect(TokenType.IDENTIFIER).value();
                fields.put(fieldName, new Twin<>(totalSize, totalSize + size));
            }

            totalSize += size;

            if (peek().type() == TokenType.COMMA) {
                consume();
            } else {
                break;
            }
        }

        expect(TokenType.RPAREN);
        expect(TokenType.SEMICOLON);

        return new TypeDefNode(name, fields, totalSize);
    }
}
