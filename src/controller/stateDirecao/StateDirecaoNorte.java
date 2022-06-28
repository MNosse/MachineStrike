package controller.stateDirecao;

import global.EnumDirecao;
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
    public String getCaminhoImagem() {
        String auxiliar = maquina.getNome().replace(" ", "");
        return "src/images/"+auxiliar+"-Norte.png";
    }
}
