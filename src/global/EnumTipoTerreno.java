package global;

public enum EnumTipoTerreno {
    ABISMO("Abismo", "controller.builderTerreno.ConstruirAbismo"),
    PANTANO("Pantano", "controller.builderTerreno.ConstruirPantano"),
    PASTO("Pasto", "controller.builderTerreno.ConstruirPasto"),
    FLORESTA("Floresta", "controller.builderTerreno.ConstruirFloresta"),
    ELEVACAO("Elevacao", "controller.builderTerreno.ConstruirElevacao"),
    MONTANHA("Montanha", "controller.builderTerreno.ConstruirMontanha");

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
