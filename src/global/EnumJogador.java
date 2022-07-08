package global;

public enum EnumJogador {
    JOGADOR1("Jogador 1"),
    JOGADOR2("Jogador 2");

    private String nome;

    EnumJogador(String nome){
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
}
