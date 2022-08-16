package model.visitor;

import model.Jogo;

public class VisitorJogadorDefensorGanhador implements Visitor {
    @Override
    public boolean visit(Jogo jogo) {
        if(jogo.jogadorDefensor().getPontosVitoria() >= 7 || jogo.jogadorAtivo().getMaquinas().size() == 0) {
            return true;
        }
        return false;
    }
}
