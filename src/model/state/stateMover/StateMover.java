package model.state.stateMover;

//MODEL
import model.Maquina;

public abstract class StateMover {

    protected Maquina maquina;

    public StateMover(Maquina maquina) {
        this.maquina = maquina;
    }

    public abstract void mover(int novaLinha, int novaColuna);

    public abstract boolean isAtivo();
}
