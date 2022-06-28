package controller.stateDirecao;

import global.EnumDirecao;
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
