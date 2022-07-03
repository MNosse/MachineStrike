package global;

public enum EnumMaquinas {
    ARIETE1("Ariete 1", "controller.builderMaquina.ConstruirAriete1"),
    ARIETE2("Ariete 2", "controller.builderMaquina.ConstruirAriete2"),
    ARRANCADA("Arrancada", "controller.builderMaquina.ConstruirArrancada"),
    ATIRADOR1("Atirador 1", "controller.builderMaquina.ConstruirAtirador1"),
    ATIRADOR2("Atirador 2", "controller.builderMaquina.ConstruirAtirador2"),
    CORPO_A_CORPO1("Corpo a Corpo 1", "controller.builderMaquina.ConstruirCorpoACorpo1"),
    CORPO_A_CORPO2("Corpo a Corpo 2", "controller.builderMaquina.ConstruirCorpoACorpo2"),
    MERGULHO1("Mergulho 1", "controller.builderMaquina.ConstruirMergulho1"),
    MERGULHO2("Mergulho 2", "controller.builderMaquina.ConstruirMergulho2"),
    PUXAO("Puxao", "controller.builderMaquina.ConstruirPuxao");

    private String nome;
    private String caminhoBuilder;

    EnumMaquinas(String nome, String caminhoBuilder) {
        this.nome = nome;
        this.caminhoBuilder = caminhoBuilder;
    }

    public String getNome() {
        return nome;
    }

    public String getCaminhoBuilder() {
        return caminhoBuilder;
    }
}
