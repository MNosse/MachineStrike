package model.state.stateMover;

import global.Exception.JaMovimentouException;
import model.Terreno;
import model.maquinas.Maquina;

import java.util.List;

public class StateMoverInativo extends StateMover {
    
    public StateMoverInativo(Maquina maquina) {
        super(maquina);
    }
    
    @Override
    public void mover(int novaLinha, int novaColuna, Terreno terrenoNaPosicao, List<Maquina> maquinasEmJogo) throws JaMovimentouException {
        throw new JaMovimentouException();
    }
    
    @Override
    public boolean isAtivo() {
        return false;
    }
}
