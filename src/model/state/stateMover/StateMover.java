package model.state.stateMover;

//MODEL
import model.Maquina;

import java.util.List;

public abstract class StateMover {

    protected Maquina maquina;

    public StateMover(Maquina maquina) {
        this.maquina = maquina;
    }

    public abstract void mover(int novaLinha, int novaColuna, List<Maquina> maquinasEmJogo);

    public abstract boolean isAtivo();
}
