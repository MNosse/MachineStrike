package model.state.stateCorrer;

import model.Terreno;
import model.maquinas.Maquina;

import java.util.List;

public class StateCorrerInativo extends StateCorrer {
    
    public StateCorrerInativo(Maquina maquina) {
        super(maquina);
    }
    
    @Override
    public void correr(int novaLinha, int novaColuna, Terreno terrenoNaPosicao, List<Maquina> maquinasEmJogo) {
        throw new RuntimeException();
    }
    
    @Override
    public boolean isAtivo() {
        return false;
    }
}
