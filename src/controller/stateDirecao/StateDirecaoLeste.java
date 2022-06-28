package controller.stateDirecao;

import global.EnumDirecao;
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
    public String getCaminhoImagem() {
        String auxiliar = maquina.getNome().replace(" ", "");
        return "src/images/"+auxiliar+"-Leste.png";
    }
}
