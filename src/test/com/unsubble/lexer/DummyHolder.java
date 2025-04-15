package test.com.unsubble.lexer;

import com.unsubble.evo.common.Holder;
import com.unsubble.evo.token.Token;

import java.util.List;

public class DummyHolder implements Holder<List<Token>> {
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