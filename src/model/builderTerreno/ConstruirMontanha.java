package model.builderTerreno;

//GLOBAL
import global.EnumTipoTerreno;

public class ConstruirMontanha extends ConstruirTerreno {

    @Override
    public void construirTipo() {
        terreno.setTipo(EnumTipoTerreno.MONTANHA);
    }

    public void construirPontosDeCombate() {
        terreno.setPontosDeCombate(3);
    }
}
