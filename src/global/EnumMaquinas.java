package global;

public enum EnumMaquinas {
    ARIETE1("Ariete 1"),
    ARIETE2("Ariete 2"),
    ARRANCADA("Arrancada"),
    ATIRADOR1("Atirador 1"),
    ATIRADOR2("Atirador 2"),
    CORPO_A_CORPO1("Corpo a Corpo 1"),
    CORPO_A_CORPO2("Corpo a Corpo 2"),
    MERGULHO1("Mergulho 1"),
    MERGULHO2("Mergulho 2"),
    PUXAO("Puxao");

    private String nome;

    EnumMaquinas(String nome){
        this.nome = nome;
    }

    public String getNome(){
        return nome;
    }
}
