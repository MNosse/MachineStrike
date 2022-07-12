package model.builderTerreno;

//GLOBAL
import global.EnumTipoTerreno;

public class ConstruirElevacao extends ConstruirTerreno {

    @Override
    public void construirTipo() {
        terreno.setTipo(EnumTipoTerreno.ELEVACAO);
    }

    public void construirPontosDeCombate() {
        terreno.setPontosDeCombate(2);
    }
}
