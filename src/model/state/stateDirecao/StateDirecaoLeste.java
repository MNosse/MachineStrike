package model.state.stateDirecao;

//GLOBAL
import global.EnumDirecao;

//MODEL
import model.Maquina;

public class StateDirecaoLeste extends StateDirecao {

    public StateDirecaoLeste(Maquina maquina) {
        super(maquina);
    }

    @Override
    public void girar() {
        maquina.setDirecaoAtual(new StateDirecaoSul(maquina));
    }

    @Override
    public EnumDirecao getDirecaoAtual() {
        return EnumDirecao.LESTE;
    }

    @Override
    public int getResistenciaBaixo() {
        return maquina.getResistenciaDireita();
    }

    @Override
    public int getResistenciaDireita() {
        return maquina.getResistenciaFrente();
    }

    @Override
    public int getResistenciaEsquerda() {
        return maquina.getResistenciaTras();
    }

    @Override
    public int getResistenciaCima() {
        return maquina.getResistenciaEsquerda();
    }

    @Override
    public String getCaminhoImagem() {
        String auxiliar = maquina.getNome().replace(" ", "");
        return "src/images/"+auxiliar+"-Leste.png";
    }
}
