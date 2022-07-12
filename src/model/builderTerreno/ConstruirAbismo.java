package model.builderTerreno;

//GLOBAL
import global.EnumTipoTerreno;

public class ConstruirAbismo extends ConstruirTerreno {

    @Override
    public void construirTipo() {
        terreno.setTipo(EnumTipoTerreno.ABISMO);
    }

    public void construirPontosDeCombate() {
        terreno.setPontosDeCombate(-2);
    }
}
