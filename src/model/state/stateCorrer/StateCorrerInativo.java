package model.state.stateCorrer;

import model.Maquina;

public class StateCorrerInativo extends StateCorrer{

    public StateCorrerInativo(Maquina maquina) {
        super(maquina);
    }

    @Override
    public void correr(int novaLinha, int novaColuna) {

    }

    @Override
    public boolean isAtivo() {
        return false;
    }
}
