package model.state.stateJogadorAtivo;

import global.Enum.EnumJogador;
import model.Jogo;

public class StateJogador2 extends StateJogadorAtivo {
    
    public StateJogador2(Jogo jogo) {
        super(jogo);
    }
    
    @Override
    public void passarTurno() {
        jogo.setJogadorAtivo(new StateJogador1(jogo));
    }
    
    @Override
    public EnumJogador getJogadorAtivo() {
        return EnumJogador.JOGADOR2;
    }
}
