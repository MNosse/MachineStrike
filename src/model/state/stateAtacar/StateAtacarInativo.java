package model.state.stateAtacar;

//MODEL
import model.Maquina;
import model.Terreno;
import model.visitor.VisitorAtaque;

import java.util.List;

public class StateAtacarInativo extends StateAtacar {

    public StateAtacarInativo(Maquina maquina) {
        super(maquina);
    }

    @Override
    public void atacar(Maquina outraMaquina, Terreno outraTerreno, List<Maquina> maquinasMesmoJogador, VisitorAtaque visitor) {
        throw new RuntimeException();
    }

    @Override
    public boolean isAtivo() {
        return false;
    }
}
