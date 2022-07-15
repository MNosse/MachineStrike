package model.state.stateMover;

//MODEL
import model.Jogo;
import model.Maquina;
import model.state.stateCorrer.StateCorrerInativo;
import model.state.StateSobrecarregar.StateSobrecarregarAtivo;

import java.util.List;

public class StateMoverAtivo extends StateMover{

    public StateMoverAtivo(Maquina maquina) {
        super(maquina);
    }

    @Override
    public void mover(int novaLinha, int novaColuna, List<Maquina> maquinasEmJogo) {
        if(maquina.podeMover(novaLinha, novaColuna, maquinasEmJogo)) {
            maquina.setLinha(novaLinha);
            maquina.setColuna(novaColuna);
            maquina.setMoverAtual(new StateMoverInativo(maquina));
            maquina.setCorrerAtual(new StateCorrerInativo(maquina));
            if (!maquina.isAtacarAtivo() && !maquina.getJaSobrecarregou()) {
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
