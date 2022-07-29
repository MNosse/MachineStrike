package global.Exception;

public class LimiteDeAcoesException extends RuntimeException {
    
    public LimiteDeAcoesException() {
        super("Voce atingiu o limite de acoes com maquinas distintas");
    }
    
}
