package global;

public enum EnumTipoTerreno {
    ABISMO("Abismo", "model.builderTerreno.ConstruirAbismo"),
    PANTANO("Pantano", "model.builderTerreno.ConstruirPantano"),
    PASTO("Pasto", "model.builderTerreno.ConstruirPasto"),
    FLORESTA("Floresta", "model.builderTerreno.ConstruirFloresta"),
    ELEVACAO("Elevacao", "model.builderTerreno.ConstruirElevacao"),
    MONTANHA("Montanha", "model.builderTerreno.ConstruirMontanha");

    private String tipo;
    private String nomeBuilder;

    EnumTipoTerreno(String tipo, String nomeBuilder) {
        this.tipo = tipo;
        this.nomeBuilder = nomeBuilder;
    }

    public String getTipo() {
        return tipo;
    }

    public String getNomeBuilder() {
        return nomeBuilder;
    }
}
