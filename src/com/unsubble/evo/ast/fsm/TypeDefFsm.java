package com.unsubble.evo.ast.fsm;

import com.unsubble.evo.ast.NodeFsm;
import com.unsubble.evo.ast.node.TypeDefNode;
import com.unsubble.evo.common.Twin;
import com.unsubble.evo.parser.ParseResult;
import com.unsubble.evo.token.TokenStream;
import com.unsubble.evo.token.TokenType;
import com.unsubble.evo.token.Token;

import java.util.*;

public class TypeDefFsm implements NodeFsm {
    @Override
    public boolean matches(List<Token> tokens, int index) {
        return tokens.get(index).type() == TokenType.TYPE;
    }

    @Override
    public ParseResult parse(List<Token> tokens, int index) {
        TokenStream stream = new TokenStream(tokens, index);

        stream.expect(TokenType.TYPE);
        String name = stream.expect(TokenType.IDENTIFIER).value();
        stream.expect(TokenType.LPAREN);

        Map<String, Twin<Integer>> fields = new LinkedHashMap<>();
        int totalSize = 0;

        while (!stream.match(TokenType.RPAREN)) {
            int size = Integer.parseInt(stream.expect(TokenType.NUMBER).value());

            if (stream.match(TokenType.COLON)) {
                stream.consume();
                String fieldName = stream.expect(TokenType.IDENTIFIER).value();
                fields.put(fieldName, new Twin<>(totalSize, totalSize + size));
            }

            totalSize += size;

            if (stream.match(TokenType.COMMA)) {
                stream.consume();
            } else {
                break;
            }
        }

        stream.expect(TokenType.RPAREN);
        stream.expect(TokenType.SEMICOLON);

        return new ParseResult(new TypeDefNode(name, fields, totalSize), stream.currentIndex());
    }
}
