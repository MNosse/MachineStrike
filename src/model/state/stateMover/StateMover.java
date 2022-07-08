package model.state.stateMover;

import model.Maquina;

public abstract class StateMover {

    protected Maquina maquina;

    public StateMover(Maquina maquina) {
        this.maquina = maquina;
    }

    public abstract void mover(int novaLinha, int novaColuna);
}
