package com.unsubble.evo.parser;

import com.unsubble.evo.ast.fsm.FunctionFsm;
import com.unsubble.evo.ast.fsm.TypeDefFsm;
import com.unsubble.evo.token.Token;
import com.unsubble.evo.ast.*;
import com.unsubble.evo.token.TokenType;

import java.util.ArrayList;
import java.util.List;

public class Parser {
    private final List<NodeFsm> fsms;

    public Parser() {
        fsms = List.of(
                new TypeDefFsm(),
                new FunctionFsm()
        );
    }

    public List<AstNode> parse(List<Token> tokens) {
        List<AstNode> result = new ArrayList<>();
        int index = 0;

        while (index < tokens.size()) {
            boolean matched = false;

            for (NodeFsm fsm : fsms) {
                if (fsm.matches(tokens, index)) {
                    ParseResult parsed = fsm.parse(tokens, index);
                    result.add(parsed.node());
                    index = parsed.nextIndex();
                    matched = true;
                    break;
                }
            }

            if (!matched) {
                if (tokens.get(index).type() == TokenType.EOF)
                    return result;
                throw new RuntimeException("Undefined syntax, token: " + tokens.get(index));
            }
        }

        throw new RuntimeException("The token of EOF is not reachable!");
    }
}