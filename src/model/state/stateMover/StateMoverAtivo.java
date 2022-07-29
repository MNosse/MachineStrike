package model.state.stateMover;

import model.Terreno;
import model.maquinas.Maquina;
import model.state.StateSobrecarregar.StateSobrecarregarAtivo;

import java.util.List;

public class StateMoverAtivo extends StateMover {
    
    public StateMoverAtivo(Maquina maquina) {
        super(maquina);
    }
    
    @Override
    public void mover(int novaLinha, int novaColuna, Terreno terrenoNaPosicao, List<Maquina> maquinasEmJogo) {
        maquina.acaoMover(novaLinha, novaColuna, terrenoNaPosicao);
        if(!maquina.isAtacarAtivo() && !maquina.getJaSobrecarregou()) {
            maquina.setSobrecarregarAtual(new StateSobrecarregarAtivo(maquina));
        }
    }
    
    @Override
    public boolean isAtivo() {
        return true;
    }
}
