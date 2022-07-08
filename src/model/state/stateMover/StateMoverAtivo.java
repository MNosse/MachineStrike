package model.state.stateMover;

import model.Maquina;

public class StateMoverAtivo extends StateMover{

    public StateMoverAtivo(Maquina maquina) {
        super(maquina);
    }

    @Override
    public void mover(int novaLinha, int novaColuna) {
        if (Math.abs((maquina.getLinha()+maquina.getColuna()) - (novaLinha+novaColuna)) <= maquina.getAlcance()) {
            maquina.setLinha(novaLinha);
            maquina.setColuna(novaColuna);
            maquina.setMoverAtual(new StateMoverInativo(maquina));
        }
    }
}
