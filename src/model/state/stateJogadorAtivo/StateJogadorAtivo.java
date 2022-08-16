package model.state.stateJogadorAtivo;

import global.Enum.EnumJogador;
import model.Jogo;

public abstract class StateJogadorAtivo {
    protected Jogo jogo;
    
    public StateJogadorAtivo(Jogo jogo) {
        this.jogo = jogo;
    }
    
    public abstract void passarTurno();
    
    public abstract EnumJogador getJogadorAtivo();
}
