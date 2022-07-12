package model.state.stateAtacar;

//MODEL
import model.Maquina;
import model.Terreno;
import model.visitor.VisitorAtaque;

public abstract class StateAtacar {

    protected Maquina maquina;

    public StateAtacar(Maquina maquina) {
        this.maquina = maquina;
    }

    public abstract void atacar(Maquina outraMaquina, Terreno outraTerreno, VisitorAtaque visitor);

    public abstract boolean isAtivo();
}
