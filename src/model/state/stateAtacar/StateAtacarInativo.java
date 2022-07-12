package model.state.stateAtacar;

//MODEL
import model.Maquina;
import model.Terreno;
import model.visitor.VisitorAtaque;

public class StateAtacarInativo extends StateAtacar {

    public StateAtacarInativo(Maquina maquina) {
        super(maquina);
    }

    @Override
    public void atacar(Maquina outraMaquina, Terreno outraTerreno, VisitorAtaque visitor) {

    }

    @Override
    public boolean isAtivo() {
        return false;
    }
}
