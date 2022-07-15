package model.state.stateJogadorAtivo;

//GLOBAL
import global.Enum.EnumJogador;

//MODEL
import model.Jogo;

public abstract class StateJogadorAtivo {
    protected Jogo jogo;

    public StateJogadorAtivo(Jogo jogo) {
        this.jogo = jogo;
    }

    public abstract void passarTurno();

    public abstract EnumJogador getJogadorAtivo();
}
