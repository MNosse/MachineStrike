package model.state.stateMover;

import model.Terreno;
import model.maquinas.Maquina;

import java.util.List;

public class StateMoverAtivo extends StateMover {
    
    public StateMoverAtivo(Maquina maquina) {
        super(maquina);
    }
    
    @Override
    public void mover(int novaLinha, int novaColuna, Terreno terrenoNaPosicao, List<Maquina> maquinasEmJogo) {
        if(maquina.podeMover(novaLinha, novaColuna, terrenoNaPosicao, maquinasEmJogo)) {
            maquina.acaoMover(novaLinha, novaColuna, terrenoNaPosicao, maquinasEmJogo);
        } else {
            throw new RuntimeException();
        }
    }
    
    @Override
    public boolean isAtivo() {
        return true;
    }
}
