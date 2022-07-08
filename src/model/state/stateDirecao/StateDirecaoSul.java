package model.state.stateDirecao;

//GLOBAL
import global.EnumDirecao;

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
    public String getCaminhoImagem() {
        String auxiliar = maquina.getNome().replace(" ", "");
        return "src/images/"+auxiliar+"-Sul.png";
    }
}
