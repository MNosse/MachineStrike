package model.state.StateSobrecarregar;

//MODEL
import model.Maquina;

public abstract class StateSobrecarregar {
    protected Maquina maquina;

    public StateSobrecarregar(Maquina maquina) {
        this.maquina = maquina;
    }

    public abstract void sobrecarregar();

    public abstract boolean isAtivo();
}
