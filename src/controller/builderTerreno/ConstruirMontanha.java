package controller.builderTerreno;

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

    public void construirCaminhoDaImage() {
        terreno.setCaminhoDaImagem("src/images/Montanha.png");
    }
}
