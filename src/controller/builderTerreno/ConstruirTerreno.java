package controller.builderTerreno;

//MODEL
import model.Terreno;

public abstract class ConstruirTerreno {

    protected Terreno terreno;

    public void construirTipo() {}

    public void construirPontosDeCombate() {}

    public void construirCaminhoDaImage() {}

    public Terreno getTerreno() {
        return terreno;
    }

    public void reset() {
        terreno = new Terreno();
    }
}
