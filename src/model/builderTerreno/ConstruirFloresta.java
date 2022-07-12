package model.builderTerreno;

//GLOBAL
import global.EnumTipoTerreno;

public class ConstruirFloresta extends ConstruirTerreno {

    @Override
    public void construirTipo() {
        terreno.setTipo(EnumTipoTerreno.FLORESTA);
    }

    public void construirPontosDeCombate() {
        terreno.setPontosDeCombate(1);
    }
}
