package model.state.StateSobrecarregar;

import model.maquinas.Maquina;

public class StateSobrecarregarAtivo extends StateSobrecarregar {
    
    public StateSobrecarregarAtivo(Maquina maquina) {
        super(maquina);
    }
    
    @Override
    public void sobrecarregar() {
        maquina.setJaSobrecarregou(true);
        maquina.setVida(maquina.getVida() - 2);
        maquina.reativarAcoes();
        maquina.setSobrecarregarAtual(new StateSobrecarregarInativo(maquina));
    }
    
    @Override
    public boolean isAtivo() {
        return true;
    }
}
