package model.builderTerreno;

//GLOBAL
import global.Enum.EnumTipoTerreno;

public class ConstruirPantano extends ConstruirTerreno {

    @Override
    public void construirTipo() {
        terreno.setTipo(EnumTipoTerreno.PANTANO);
    }

    public void construirPontosDeCombate() {
        terreno.setPontosDeCombate(-1);
    }
}
