package model.state.stateCorrer;

import model.Maquina;
import model.state.stateMover.StateMoverInativo;

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
            System.out.println("Adicionar os proximos states aqui");
        }
    }

    @Override
    public boolean isAtivo() {
        return true;
    }
}
