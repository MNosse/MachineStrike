package model.builderTerreno;

import model.Terreno;

public abstract class ConstruirTerreno {
    
    protected Terreno terreno;
    
    public void construirTipo() {
    }
    
    public void construirPontosDeCombate() {
    }
    
    public Terreno getTerreno() {
        return terreno;
    }
    
    public void reset() {
        terreno = new Terreno();
    }
}
