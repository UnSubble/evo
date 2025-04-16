package com.unsubble.evo.lexer;

import com.unsubble.evo.common.Holder;
import com.unsubble.evo.common.Status;
import com.unsubble.evo.token.Token;
import com.unsubble.evo.token.TokenType;

import java.util.*;

public class Lexer {
    private int index;
    private final String src;
    private boolean noEscapeMode;

    private static final List<TokenType> TOKENS = Arrays.stream(TokenType.values()).toList();
    private static final Status SUCCESS = new Status(null);

    public Lexer(String src) {
        this.src = src;
        index = 0;
        noEscapeMode = false;
    }

    public Status lex(Holder<List<Token>> holder) {
        index = 0;
        List<Token> tokens;
        if (holder.holds()) {
            tokens = holder.release();
        } else {
            tokens = new LinkedList<>();
        }

        StringBuilder builder = new StringBuilder();
        TokenType lastType = TokenType.EOF;

        while (index < src.length()) {
            char c = src.charAt(index);

            if (!noEscapeMode && !builder.isEmpty()) {
                Token token = toToken(builder.toString());

                if (isAddable(token.type(), lastType)) {
                    Token last = tokens.removeLast();
                    token = tokenizeAndClear(builder.insert(0, last.value()));
                } else {
                    builder.setLength(0);
                }

                tokens.add(token);
                lastType = token.type();
            }

            if (noEscapeMode || !Character.isWhitespace(c))
                builder.append(c);
            index++;
        }

        Token token = toToken(builder.toString());
        if (isAddable(token.type(), lastType)) {
            Token last = tokens.removeLast();
            token = tokenizeAndClear(builder.insert(0, last.value()));
        }

        tokens.add(token);
        tokens.add(new Token(TokenType.EOF, TokenType.EOF.getType()));
        holder.hold(tokens);
        return SUCCESS;
    }

    private Token tokenizeAndClear(StringBuilder builder) {
        Token token = toToken(builder.toString());
        builder.setLength(0);
        return token;
    }

    private boolean isAddable(TokenType type, TokenType lastType) {
        return (TokenType.IDENTIFIER == type || TokenType.NUMBER == type) &&
                (TokenType.IDENTIFIER == lastType || TokenType.NUMBER == lastType) ||
                lastType == type && type.getType().matches("^[&/<>=|]+$");
    }

    public Token toToken(String tokenStr) {
        for (TokenType type : TOKENS) {
            if (tokenStr.equals(type.getType())) {
                return new Token(type, tokenStr);
            }
        }
        if (tokenStr.matches("^(?:0[xX][0-9a-fA-F]+|0[bB][01]+|0[oO][0-7]+|(?:\\d+\\.?\\d*|\\.\\d+)(?:[eE]\\d+)?|\\d+)$"))
            return new Token(TokenType.NUMBER, tokenStr);
        return new Token(TokenType.IDENTIFIER, tokenStr);
    }
}
