package global;

public enum EnumMaquinas {
    ARIETE1("Ariete 1", "model.builderMaquina.ConstruirAriete1"),
    ARIETE2("Ariete 2", "model.builderMaquina.ConstruirAriete2"),
    ARRANCADA("Arrancada", "model.builderMaquina.ConstruirArrancada"),
    ATIRADOR1("Atirador 1", "model.builderMaquina.ConstruirAtirador1"),
    ATIRADOR2("Atirador 2", "model.builderMaquina.ConstruirAtirador2"),
    CORPO_A_CORPO1("Corpo a Corpo 1", "model.builderMaquina.ConstruirCorpoACorpo1"),
    CORPO_A_CORPO2("Corpo a Corpo 2", "model.builderMaquina.ConstruirCorpoACorpo2"),
    MERGULHO1("Mergulho 1", "model.builderMaquina.ConstruirMergulho1"),
    MERGULHO2("Mergulho 2", "model.builderMaquina.ConstruirMergulho2"),
    PUXAO("Puxao", "model.builderMaquina.ConstruirPuxao");

    private String nome;
    private String nomeBuilder;

    EnumMaquinas(String nome, String nomeBuilder) {
        this.nome = nome;
        this.nomeBuilder = nomeBuilder;
    }

    public String getNome() {
        return nome;
    }

    public String getNomeBuilder() {
        return nomeBuilder;
    }
}
