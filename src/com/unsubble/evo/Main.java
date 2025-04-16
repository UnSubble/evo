package com.unsubble.evo;

import com.unsubble.evo.ast.AstNode;
import com.unsubble.evo.common.Holder;
import com.unsubble.evo.common.Status;
import com.unsubble.evo.lexer.Lexer;
import com.unsubble.evo.parser.Parser;
import com.unsubble.evo.token.Token;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Holder<List<Token>> holder = new DummyHolder();
        Status status = new Lexer("""
                function test(a, b) {
                    return 65 + 12 - 6 * ((4 - 4) + 2);
                }""").lex(holder);
        Parser parser = new Parser();
        List<Token> tokens = holder.release();
        System.out.println(tokens);
        List<AstNode> nodes = parser.parse(tokens);
        System.out.println(nodes);
    }
}

class DummyHolder implements Holder<List<Token>> {

    protected List<Token> list;

    @Override
    public void hold(List<Token> obj) {
        this.list = obj;
    }

    @Override
    public boolean holds() {
        return list != null;
    }

    @Override
    public List<Token> release() {
        List<Token> list = this.list;
        this.list = null;
        return list;
    }

    @Override
    public List<Token> get() {
        return list;
    }
}