package global.Enum;

public enum EnumTipoMaquinas {
    CORPO_A_CORPO("Corpo a corpo"),
    ATIRADOR("Atirador"),
    ARRANCADA("Arrancada"),
    ARIETE("Ariete"),
    PUXAO("Puxao"),
    MERGULHO("Mergulho");

    private String tipo;

    EnumTipoMaquinas(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

}