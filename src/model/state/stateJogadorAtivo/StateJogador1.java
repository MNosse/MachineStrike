package model.state.stateJogadorAtivo;

//GLOBAL
import global.EnumJogador;

public class StateJogador1 extends StateJogadorAtivo{
    @Override
    public void passarTurno() {
        jogo.setJogadorAtivo(new StateJogador1());
    }

    @Override
    public void girar() {

    }

    @Override
    public void mover() {

    }

    @Override
    public void atacar() {

    }

    @Override
    public void sobrecarregar() {

    }

    @Override
    public EnumJogador getJogadorAtivo() {
        return EnumJogador.JOGADOR1;
    }
}
