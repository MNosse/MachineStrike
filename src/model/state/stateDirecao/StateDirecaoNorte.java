package model.state.stateDirecao;

//GLOBAL
import global.EnumDirecao;

//MODEL
import model.Maquina;

public class StateDirecaoNorte extends StateDirecao {

    public StateDirecaoNorte(Maquina maquina) {
        super(maquina);
    }

    @Override
    public void girar() {
        maquina.setDirecaoAtual(new StateDirecaoLeste(maquina));
    }

    @Override
    public EnumDirecao getDirecaoAtual() {
        return EnumDirecao.NORTE;
    }

    @Override
    public int getResistenciaBaixo() {
        return maquina.getResistenciaTras();
    }

    @Override
    public int getResistenciaDireita() {
        return maquina.getResistenciaDireita();
    }

    @Override
    public int getResistenciaEsquerda() {
        return maquina.getResistenciaEsquerda();
    }

    @Override
    public int getResistenciaCima() {
        return maquina.getResistenciaFrente();
    }

    @Override
    public String getCaminhoImagem() {
        String auxiliar = maquina.getNome().replace(" ", "");
        return "src/images/"+auxiliar+"-Norte.png";
    }
}
