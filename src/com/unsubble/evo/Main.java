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
                type MyDef(4, 8: a, 4);""").lex(holder);
        Parser parser = new Parser(holder.get());
        List<Token> tokens = holder.release();
        AstNode node = parser.parseTypeDef();
        System.out.println(node);
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