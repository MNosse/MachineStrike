package model.state.stateCorrer;

import model.Terreno;
import model.maquinas.Maquina;

import java.util.List;

public class StateCorrerAtivo extends StateCorrer {
    
    public StateCorrerAtivo(Maquina maquina) {
        super(maquina);
    }
    
    @Override
    public void correr(int novaLinha, int novaColuna, Terreno terrenoNaPosicao, List<Maquina> maquinasEmJogo) {
        if(maquina.podeCorrer(novaLinha, novaColuna, terrenoNaPosicao, maquinasEmJogo)) {
            maquina.acaoCorrer(novaLinha, novaColuna, terrenoNaPosicao, maquinasEmJogo);
        } else {
            throw new RuntimeException();
        }
    }
    
    @Override
    public boolean isAtivo() {
        return true;
    }
}
