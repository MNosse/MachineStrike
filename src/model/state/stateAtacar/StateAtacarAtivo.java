package model.state.stateAtacar;

//MODEL
import model.Maquina;
import model.Terreno;
import model.visitor.VisitorAtaque;
import model.state.StateSobrecarregar.StateSobrecarregarAtivo;

public class StateAtacarAtivo extends StateAtacar {

    public StateAtacarAtivo(Maquina maquina) {
        super(maquina);
    }

    @Override
    public void atacar(Maquina outraMaquina, Terreno outraTerreno, VisitorAtaque visitor) {
        if(maquina.podeAtacar(outraMaquina)) {
            visitor.atacar(outraMaquina, outraTerreno);
            maquina.setAtacarAtual(new StateAtacarInativo(maquina));
            if (!maquina.isMoverAtivo() && !maquina.getJaSobrecarregou()) {
                maquina.setSobrecarregarAtual(new StateSobrecarregarAtivo(maquina));
            }
        }
    }

    @Override
    public boolean isAtivo() {
        return true;
    }
}
