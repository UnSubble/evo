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


}
