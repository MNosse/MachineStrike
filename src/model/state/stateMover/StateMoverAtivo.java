package model.state.stateMover;

//MODEL
import model.Maquina;
import model.state.stateCorrer.StateCorrerInativo;
import model.state.StateSobrecarregar.StateSobrecarregarAtivo;

public class StateMoverAtivo extends StateMover{

    public StateMoverAtivo(Maquina maquina) {
        super(maquina);
    }

    @Override
    public void mover(int novaLinha, int novaColuna) {
        if(maquina.podeMover(novaLinha, novaColuna)) {
            maquina.setLinha(novaLinha);
            maquina.setColuna(novaColuna);
            maquina.setMoverAtual(new StateMoverInativo(maquina));
            maquina.setCorrerAtual(new StateCorrerInativo(maquina));
            if (!maquina.isAtacarAtivo() && !maquina.getJaSobrecarregou()) {
                maquina.setSobrecarregarAtual(new StateSobrecarregarAtivo(maquina));
            }
        }
    }

    @Override
    public boolean isAtivo() {
        return true;
    }
}
