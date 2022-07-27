package global.Exception;

public class SubstituicaoInvalidaException extends RuntimeException {
    
    public SubstituicaoInvalidaException() {
        super("A troca dessas maquinas ira ultrapassar o limite de PV");
    }
    
}
