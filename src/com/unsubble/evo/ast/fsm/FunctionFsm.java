package com.unsubble.evo.ast.fsm;

import com.unsubble.evo.ast.AstNode;
import com.unsubble.evo.ast.NodeFsm;
import com.unsubble.evo.ast.node.FunctionNode;
import com.unsubble.evo.ast.node.ReturnNode;
import com.unsubble.evo.parser.ExprParser;
import com.unsubble.evo.parser.ParseResult;
import com.unsubble.evo.token.*;

import java.util.*;

public class FunctionFsm implements NodeFsm {
    @Override
    public boolean matches(List<Token> tokens, int index) {
        return tokens.get(index).type() == TokenType.FUNCTION;
    }

    @Override
    public ParseResult parse(List<Token> tokens, int index) {
        TokenStream stream = new TokenStream(tokens, index);

        stream.expect(TokenType.FUNCTION);
        String functionName = stream.expect(TokenType.IDENTIFIER).value();
        stream.expect(TokenType.LPAREN);

        List<String> parameters = new ArrayList<>();
        while (!stream.match(TokenType.RPAREN)) {
            parameters.add(stream.expect(TokenType.IDENTIFIER).value());
            if (stream.match(TokenType.COMMA)) {
                stream.consume();
            } else {
                break;
            }
        }

        stream.expect(TokenType.RPAREN);
        stream.expect(TokenType.LBRACE);

        List<AstNode> body = new ArrayList<>();
        ExprParser exprParser = new ExprParser(stream);
        while (!stream.match(TokenType.RBRACE)) {
            if (stream.match(TokenType.RETURN)) {
                stream.consume();
                AstNode expr = exprParser.parse();
                stream.expect(TokenType.SEMICOLON);
                body.add(new ReturnNode(expr));
            } else {
                throw new RuntimeException("Unexpected expression: " + stream.peek());
            }
        }

        stream.expect(TokenType.RBRACE);

        return new ParseResult(new FunctionNode(functionName, parameters, body), stream.currentIndex());
    }
}
