package model.state.stateAtacar;

//JAVA
import java.util.List;

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
    public void atacar(Maquina outraMaquina, Terreno outraTerreno, List<Maquina> maquinasMesmoJogador, VisitorAtaque visitor) {
        if(maquina.podeAtacar(outraMaquina, maquinasMesmoJogador)) {
            visitor.atacar(outraMaquina, outraTerreno);
            maquina.setAtacarAtual(new StateAtacarInativo(maquina));
            if (!maquina.isMoverAtivo() && !maquina.getJaSobrecarregou()) {
                maquina.setSobrecarregarAtual(new StateSobrecarregarAtivo(maquina));
            }
        } else {
            throw new RuntimeException();
        }
    }

    @Override
    public boolean isAtivo() {
        return true;
    }
}
