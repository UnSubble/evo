package com.unsubble.evo.ast;

import com.unsubble.evo.parser.ParseResult;
import com.unsubble.evo.token.Token;
import java.util.List;

public interface NodeFsm {

    boolean matches(List<Token> tokens, int index);

    ParseResult parse(List<Token> tokens, int index);
}
