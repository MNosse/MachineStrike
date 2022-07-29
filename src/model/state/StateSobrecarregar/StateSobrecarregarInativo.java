package model.state.StateSobrecarregar;

import global.Exception.JaSobrecarregouException;
import model.maquinas.Maquina;

public class StateSobrecarregarInativo extends StateSobrecarregar {
    
    public StateSobrecarregarInativo(Maquina maquina) {
        super(maquina);
    }
    
    @Override
    public void sobrecarregar() {
        throw new JaSobrecarregouException();
    }
    
    @Override
    public boolean isAtivo() {
        return false;
    }
}
