package model.builderTerreno;

import global.Enum.EnumTipoTerreno;

public class ConstruirMontanha extends ConstruirTerreno {
    
    @Override
    public void construirTipo() {
        terreno.setTipo(EnumTipoTerreno.MONTANHA);
    }
    
    public void construirPontosDeCombate() {
        terreno.setPontosDeCombate(3);
    }
}
