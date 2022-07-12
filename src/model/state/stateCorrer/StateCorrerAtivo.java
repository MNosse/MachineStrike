package model.state.stateCorrer;

//MODEL
import model.Maquina;
import model.state.stateMover.StateMoverInativo;
import model.state.stateAtacar.StateAtacarInativo;
import model.state.StateSobrecarregar.StateSobrecarregarAtivo;

public class StateCorrerAtivo extends StateCorrer{

    public StateCorrerAtivo(Maquina maquina) {
        super(maquina);
    }

    @Override
    public void correr(int novaLinha, int novaColuna) {
        if(maquina.podeCorrer(novaLinha, novaColuna)) {
            maquina.setLinha(novaLinha);
            maquina.setColuna(novaColuna);
            maquina.setMoverAtual(new StateMoverInativo(maquina));
            maquina.setCorrerAtual(new StateCorrerInativo(maquina));
            maquina.setAtacarAtual(new StateAtacarInativo(maquina));
            if (!maquina.getJaSobrecarregou()) {
                maquina.setSobrecarregarAtual(new StateSobrecarregarAtivo(maquina));
            }
        }
    }

    @Override
    public boolean isAtivo() {
        return true;
    }
}
