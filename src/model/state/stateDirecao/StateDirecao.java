package model.state.stateDirecao;

//GLOBAL
import global.EnumDirecao;

//MODEL
import model.Maquina;

public abstract class StateDirecao {

    protected Maquina maquina;

    public StateDirecao(Maquina maquina) {
        this.maquina = maquina;
    }

    public abstract void girar();

    public abstract EnumDirecao getDirecaoAtual();

    public abstract String getCaminhoImagem();
}
