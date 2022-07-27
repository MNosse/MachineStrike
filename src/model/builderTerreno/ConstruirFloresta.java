package model.builderTerreno;

import global.Enum.EnumTipoTerreno;

public class ConstruirFloresta extends ConstruirTerreno {
    
    @Override
    public void construirTipo() {
        terreno.setTipo(EnumTipoTerreno.FLORESTA);
    }
    
    public void construirPontosDeCombate() {
        terreno.setPontosDeCombate(1);
    }
}
