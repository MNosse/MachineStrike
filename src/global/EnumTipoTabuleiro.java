package global;

public enum EnumTipoTabuleiro {
    PADRAO("padrao"),
    CRIADO("criado");

    private String tipo;

    EnumTipoTabuleiro(String tipo){
        this.tipo = tipo;
    }

    public String getTipo(){
        return tipo;
    }
}
