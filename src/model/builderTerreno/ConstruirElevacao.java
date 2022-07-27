package model.builderTerreno;

import global.Enum.EnumTipoTerreno;

public class ConstruirElevacao extends ConstruirTerreno {
    
    @Override
    public void construirTipo() {
        terreno.setTipo(EnumTipoTerreno.ELEVACAO);
    }
    
    public void construirPontosDeCombate() {
        terreno.setPontosDeCombate(2);
    }
}
