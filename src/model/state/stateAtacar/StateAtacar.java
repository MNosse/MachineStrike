package model.state.stateAtacar;

import model.Tabuleiro;
import model.maquinas.Maquina;

public abstract class StateAtacar {
    
    protected Maquina maquina;
    
    public StateAtacar(Maquina maquina) {
        this.maquina = maquina;
    }
    
    public abstract void atacar(Maquina outraMaquina, Tabuleiro tabuleiro);
    
    public abstract boolean isAtivo();
}
