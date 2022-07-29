package model.state.stateCorrer;

import global.Exception.JaCorreuException;
import model.Terreno;
import model.maquinas.Maquina;

import java.util.List;

public class StateCorrerInativo extends StateCorrer {
    
    public StateCorrerInativo(Maquina maquina) {
        super(maquina);
    }
    
    @Override
    public void correr(int novaLinha, int novaColuna, Terreno terrenoNaPosicao, List<Maquina> maquinasEmJogo) throws JaCorreuException {
        throw new JaCorreuException();
    }
    
    @Override
    public boolean isAtivo() {
        return false;
    }
}
