package model.state.stateCorrer;

import model.Terreno;
import model.maquinas.Maquina;

import java.util.List;

public abstract class StateCorrer {
    
    protected Maquina maquina;
    
    public StateCorrer(Maquina maquina) {
        this.maquina = maquina;
    }
    
    public abstract void correr(int novaLinha, int novaColuna, Terreno terrenoNaPosicao, List<Maquina> maquinasEmJogo);
    
    public abstract boolean isAtivo();
}
