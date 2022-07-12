package model.state.stateCorrer;

//MODEL
import model.Maquina;

public abstract class StateCorrer {

    protected Maquina maquina;

    public StateCorrer(Maquina maquina) {
        this.maquina = maquina;
    }

    public abstract void correr(int novaLinha, int novaColuna);

    public abstract boolean isAtivo();
}
