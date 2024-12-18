package model.state.stateDirecao;

import global.Enum.EnumDirecao;
import model.maquinas.Maquina;

public class StateDirecaoOeste extends StateDirecao {
    
    public StateDirecaoOeste(Maquina maquina) {
        super(maquina);
    }
    
    @Override
    public void girar() {
        maquina.setDirecaoAtual(new StateDirecaoNorte(maquina));
    }
    
    @Override
    public EnumDirecao getDirecaoAtual() {
        return EnumDirecao.OESTE;
    }
    
    @Override
    public int getResistenciaBaixo() {
        return maquina.getResistenciaEsquerda();
    }
    
    @Override
    public int getResistenciaDireita() {
        return maquina.getResistenciaTras();
    }
    
    @Override
    public int getResistenciaEsquerda() {
        return maquina.getResistenciaFrente();
    }
    
    @Override
    public int getResistenciaCima() {
        return maquina.getResistenciaDireita();
    }
    
    @Override
    public String getCaminhoImagem() {
        String auxiliar = maquina.getNome().replace(" ", "");
        return "src/images/" + auxiliar + "-Oeste.png";
    }
}
