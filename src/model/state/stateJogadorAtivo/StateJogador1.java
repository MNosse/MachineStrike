package model.state.stateJogadorAtivo;

import global.Enum.EnumJogador;
import model.Jogo;

public class StateJogador1 extends StateJogadorAtivo {
    public StateJogador1(Jogo jogo) {
        super(jogo);
    }
    
    @Override
    public void passarTurno() {
        jogo.setJogadorAtivo(new StateJogador2(jogo));
    }
    
    @Override
    public EnumJogador getJogadorAtivo() {
        return EnumJogador.JOGADOR1;
    }
}
