package model.state.stateDirecao;

//GLOBAL
import global.Enum.EnumDirecao;

//MODEL
import model.Maquina;

public class StateDirecaoSul extends StateDirecao {

    public StateDirecaoSul(Maquina maquina) {
        super(maquina);
    }

    @Override
    public void girar() {
        maquina.setDirecaoAtual(new StateDirecaoOeste(maquina));
    }

    @Override
    public EnumDirecao getDirecaoAtual() {
        return EnumDirecao.SUL;
    }

    @Override
    public int getResistenciaBaixo() {
        return maquina.getResistenciaFrente();
    }

    @Override
    public int getResistenciaDireita() {
        return maquina.getResistenciaEsquerda();
    }

    @Override
    public int getResistenciaEsquerda() {
        return maquina.getResistenciaDireita();
    }

    @Override
    public int getResistenciaCima() {
        return maquina.getResistenciaTras();
    }

    @Override
    public String getCaminhoImagem() {
        String auxiliar = maquina.getNome().replace(" ", "");
        return "src/images/"+auxiliar+"-Sul.png";
    }
}
