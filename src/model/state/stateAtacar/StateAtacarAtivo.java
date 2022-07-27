package model.state.stateAtacar;

import model.Tabuleiro;
import model.maquinas.Maquina;
import model.state.StateSobrecarregar.StateSobrecarregarAtivo;

public class StateAtacarAtivo extends StateAtacar {
    
    public StateAtacarAtivo(Maquina maquina) {
        super(maquina);
    }
    
    @Override
    public void atacar(Maquina outraMaquina, Tabuleiro tabuleiro) {
        if(maquina.podeAtacar(outraMaquina, tabuleiro)) {
            maquina.acaoAtacar(outraMaquina, tabuleiro);
            maquina.setAtacarAtual(new StateAtacarInativo(maquina));
            if(!maquina.isMoverAtivo() && !maquina.getJaSobrecarregou()) {
                maquina.setSobrecarregarAtual(new StateSobrecarregarAtivo(maquina));
            }
        } else {
            throw new RuntimeException();
        }
    }
    
    @Override
    public boolean isAtivo() {
        return true;
    }
}
