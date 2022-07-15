package global.Enum;

public enum EnumTipoTerreno {
    ABISMO("Abismo"),
    PANTANO("Pantano"),
    PASTO("Pasto"),
    FLORESTA("Floresta"),
    ELEVACAO("Elevacao"),
    MONTANHA("Montanha");

    private String tipo;

    EnumTipoTerreno(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }
}
