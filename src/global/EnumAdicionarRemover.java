package global;

public enum EnumAdicionarRemover {
    ADICIONAR("Adicionar"),
    REMOVER("Remover");

    private String nome;

    EnumAdicionarRemover(String nome){
        this.nome = nome;
    }

    public String getNome(){
        return nome;
    }
}
