package model.visitor;

import model.Jogo;
import model.maquinas.Maquina;

import java.util.ArrayList;
import java.util.List;

public class VisitorMaquinasMortas implements Visitor{
    @Override
    public boolean visit(Jogo jogo) {
        List<Maquina> maquinasMortas = new ArrayList<>();
        for (Maquina maquina : jogo.getTabuleiro().getMaquinas()) {
            if (maquina.getVida() <= 0) {
                maquinasMortas.add(maquina);
            }
        }
        if (maquinasMortas.size() > 0) {
            for(Maquina maquina : maquinasMortas) {
                jogo.getTabuleiro().removerMaquina(maquina);
                if(jogo.jogadorAtivo().getMaquinas().contains(maquina)) {
                    jogo.jogadorAtivo().removeMaquina(maquina);
                    jogo.jogadorDefensor().addPontosVitoria(maquina.getPontosVitoria());
                } else if(jogo.jogadorDefensor().getMaquinas().contains(maquina)) {
                    jogo.jogadorDefensor().removeMaquina(maquina);
                    jogo.jogadorAtivo().addPontosVitoria(maquina.getPontosVitoria());
                }
            }
            return true;
        }
        return false;
    }
}
