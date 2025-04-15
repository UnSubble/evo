package com.unsubble.evo.token;

public enum TokenType {
    TYPE("type"),
    DECLARE("declare"),
    SIZEOF("sizeof"),
    FUNCTION("function"),
    ASYNC("async"),
    SAFE("safe"),
    NATIVE("native"),
    RETURN("return"),
    FREE("free"),
    IF("if"),
    ELSE("else"),
    FOR("for"),

    LPAREN("("),     // (
    RPAREN(")"),     // )
    LBRACE("{"),     // {
    RBRACE("}"),     // }
    COMMA(","),      // ,
    COLON(":"),      // :
    DOT("."),        // .
    SEMICOLON(";"),  // ;
    ASSIGN("="),     // =

    PLUS("+"),       // +
    MINUS("-"),      // -
    STAR("*"),       // *
    SLASH("/"),      // /
    MOD("%"),        // %

    EQUAL("=="),      // ==
    NOT_EQUAL("!="),  // !=
    LT("<"),         // <
    GT(">"),         // >
    LTE("<="),        // <=
    GTE(">="),        // >=

    AND("&&"),        // &&
    OR("||"),         // ||
    NOT("!"),        // !

    BITWISE_AND("&"),
    BITWISE_OR("|"),
    BITWISE_NOT("~"),
    BITWISE_XOR("^"),
    SHIFT_LEFT("<<"),
    SHIFT_RIGHT(">>"),
    SHIFT_RIGHT_UNSIGNED(">>>"),

    IDENTIFIER("\\w"),
    NUMBER("\\d"),

    EOF("EOF");

    private final String type;

    TokenType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
