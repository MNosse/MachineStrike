package model.state.stateAtacar;

import global.Exception.JaAtacouException;
import model.Tabuleiro;
import model.maquinas.Maquina;

public class StateAtacarInativo extends StateAtacar {
    
    public StateAtacarInativo(Maquina maquina) {
        super(maquina);
    }
    
    @Override
    public void atacar(Maquina outraMaquina, Tabuleiro tabuleiro) throws JaAtacouException {
        throw new JaAtacouException();
    }
    
    @Override
    public boolean isAtivo() {
        return false;
    }
}
