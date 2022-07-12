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

    public abstract int getResistenciaBaixo();

    public abstract int getResistenciaDireita();

    public abstract int getResistenciaEsquerda();

    public abstract int getResistenciaCima();

    public abstract String getCaminhoImagem();
}
