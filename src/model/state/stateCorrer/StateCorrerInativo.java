package model.state.stateCorrer;

//MODEL
import model.Maquina;

public class StateCorrerInativo extends StateCorrer{

    public StateCorrerInativo(Maquina maquina) {
        super(maquina);
    }

    @Override
    public void correr(int novaLinha, int novaColuna) {
        throw new RuntimeException();
    }

    @Override
    public boolean isAtivo() {
        return false;
    }
}
