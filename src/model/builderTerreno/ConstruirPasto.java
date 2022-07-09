package model.builderTerreno;

//GLOBAL
import global.EnumTipoTerreno;

public class ConstruirPasto extends ConstruirTerreno {

    @Override
    public void construirTipo() {
        terreno.setTipo(EnumTipoTerreno.PASTO);
    }

    public void construirPontosDeCombate() {
        terreno.setPontosDeCombate(0);
    }

    public void construirCaminhoDaImage() {
        terreno.setCaminhoDaImagem("src/images/Pasto.png");
    }
}
