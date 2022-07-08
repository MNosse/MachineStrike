package model.state.stateJogadorAtivo;

//GLOBAL
import global.EnumJogador;

//MODEL
import model.Jogo;

public abstract class StateJogadorAtivo {
    protected Jogo jogo;

    public abstract void passarTurno();

    public abstract void girar();

    public abstract void mover();

    public abstract void atacar();

    public abstract void sobrecarregar();

    public abstract EnumJogador getJogadorAtivo();
}
