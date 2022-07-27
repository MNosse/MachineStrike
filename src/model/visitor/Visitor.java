package model.visitor;

import model.Jogo;

public interface Visitor {
    boolean visit(Jogo jogo);
}
