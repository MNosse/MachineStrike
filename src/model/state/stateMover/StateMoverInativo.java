package model.state.stateMover;

import model.Maquina;

public class StateMoverInativo extends StateMover{

    public StateMoverInativo(Maquina maquina) {
        super(maquina);
    }

    @Override
    public void mover(int novaLinha, int novaColuna) {
        System.out.println("nao pode mover");
    }
}
