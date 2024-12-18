package model.state.stateMover;

import model.Terreno;
import model.maquinas.Maquina;

import java.util.List;

public abstract class StateMover {
    
    protected Maquina maquina;
    
    public StateMover(Maquina maquina) {
        this.maquina = maquina;
    }
    
    public abstract void mover(int novaLinha, int novaColuna, Terreno terrenoNaPosicao, List<Maquina> maquinasEmJogo);
    
    public abstract boolean isAtivo();
}
