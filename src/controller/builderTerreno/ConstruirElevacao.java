package controller.builderTerreno;

import global.EnumTipoTerreno;

public class ConstruirElevacao extends ConstruirTerreno {
    @Override
    public void construirTipo() {
        terreno.setTipo(EnumTipoTerreno.ELEVACAO);
    }

    public void construirPontosDeCombate() {
        terreno.setPontosDeCombate(2);
    }

    public void construirCaminhoDaImage() {
        terreno.setCaminhoDaImagem("src/images/Elevacao.png");
    }
}
