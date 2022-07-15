package model.builderTerreno;

//GLOBAL
import global.Enum.EnumTipoTerreno;

public class ConstruirPasto extends ConstruirTerreno {

    @Override
    public void construirTipo() {
        terreno.setTipo(EnumTipoTerreno.PASTO);
    }

    public void construirPontosDeCombate() {
        terreno.setPontosDeCombate(0);
    }
}
