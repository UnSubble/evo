package com.unsubble.evo.token;

import java.util.List;

public class TokenStream {
    private final List<Token> tokens;
    private int index;

    public TokenStream(List<Token> tokens, int start) {
        this.tokens = tokens;
        this.index = start;
    }

    public Token peek() {
        return tokens.get(index);
    }

    public Token peek(int offset) {
        return tokens.get(index + offset);
    }

    public Token consume() {
        return tokens.get(index++);
    }

    public Token expect(TokenType type) {
        Token token = consume();
        if (token.type() != type) {
            throw new RuntimeException("Expected: " + type + ", but actual: " + token.type());
        }
        return token;
    }

    public boolean match(TokenType type) {
        return peek().type() == type;
    }

    public int currentIndex() {
        return index;
    }
}
