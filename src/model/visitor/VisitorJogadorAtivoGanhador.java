package model.visitor;

import model.Jogo;

public class VisitorJogadorAtivoGanhador implements Visitor {
    @Override
    public boolean visit(Jogo jogo) {
        if(jogo.jogadorAtivo().getPontosVitoria() >= 7 || jogo.jogadorDefensor().getMaquinas().size() == 0) {
            return true;
        }
        return false;
    }
}
