package model.builderTerreno;

public class DirectorTerreno {
    
    private ConstruirTerreno construirTerreno;
    
    public DirectorTerreno(ConstruirTerreno construirTerreno) {
        this.construirTerreno = construirTerreno;
    }
    
    public void construir() {
        construirTerreno.reset();
        construirTerreno.construirTipo();
        construirTerreno.construirPontosDeCombate();
    }
}
