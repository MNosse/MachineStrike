package controller.builderTerreno;

//GLOBAL
import global.EnumTipoTerreno;

public class ConstruirPantano extends ConstruirTerreno {

    @Override
    public void construirTipo() {
        terreno.setTipo(EnumTipoTerreno.PANTANO);
    }

    public void construirPontosDeCombate() {
        terreno.setPontosDeCombate(-1);
    }

    public void construirCaminhoDaImage() {
        terreno.setCaminhoDaImagem("src/images/Pantano.png");
    }
}