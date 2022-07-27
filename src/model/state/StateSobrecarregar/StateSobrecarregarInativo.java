package model.state.StateSobrecarregar;

import model.maquinas.Maquina;

public class StateSobrecarregarInativo extends StateSobrecarregar {
    
    public StateSobrecarregarInativo(Maquina maquina) {
        super(maquina);
    }
    
    @Override
    public void sobrecarregar() {
        throw new RuntimeException();
    }
    
    @Override
    public boolean isAtivo() {
        return false;
    }
}
