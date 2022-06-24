package controller.builderTerreno;

import global.EnumTipoTerreno;

public class DirectorTerreno {
    private ConstruirTerreno construirTerreno;

    public DirectorTerreno(ConstruirTerreno construirTerreno){
        this.construirTerreno = construirTerreno;
    }

    public void construir() {
        construirTerreno.reset();
        construirTerreno.construirTipo();
        construirTerreno.construirPontosDeCombate();
        construirTerreno.construirCaminhoDaImage();
    }
}
