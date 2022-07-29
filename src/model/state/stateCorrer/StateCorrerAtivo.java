package model.state.stateCorrer;

import model.Terreno;
import model.maquinas.Maquina;
import model.state.StateSobrecarregar.StateSobrecarregarAtivo;

import java.util.List;

public class StateCorrerAtivo extends StateCorrer {
    
    public StateCorrerAtivo(Maquina maquina) {
        super(maquina);
    }
    
    @Override
    public void correr(int novaLinha, int novaColuna, Terreno terrenoNaPosicao, List<Maquina> maquinasEmJogo) {
        maquina.acaoCorrer(novaLinha, novaColuna);
        if(!maquina.getJaSobrecarregou()) {
            maquina.setSobrecarregarAtual(new StateSobrecarregarAtivo(maquina));
        }
    }
    
    @Override
    public boolean isAtivo() {
        return true;
    }
}
